package ro.ubb.donation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.core.service.CenterService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import ro.ubb.donation.core.service.UserService;
import ro.ubb.donation.web.dto.MatchingDonorDto;
import ro.ubb.donation.web.requests.ClosestDonorRequest;
import ro.ubb.donation.web.response.ClosestDonorResponse;
import ro.ubb.donation.web.utils.DistancePojo;

@RestController
public class FindDonorController {
    @Autowired
    private CenterService centerService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/matching-donors/{centerID}", method = RequestMethod.POST)
    public ClosestDonorResponse getTheClosestNeighbour(
            @PathVariable final int centerID,
            @RequestBody final ClosestDonorRequest closestDonor) {
        Optional<Center> c = centerService.findCenter(centerID);
        Map<User , Float> results = new HashMap<>();
        ClosestDonorResponse closestDonorResponse=null;
        List<MatchingDonorDto> donorsResponse;
        if (c.isPresent()) {
            String origin = replaceSpaceWithPlus(c.get().getAddress()+ " "+c.get().getCity());
            //String origin = "Cluj+Napoca"; the address should be like this
            List<User> users = userService.findAll();
            URL url = null;

            for (User u: users) {
                if (u.getAddress()!=null) {
                    String dest = replaceSpaceWithPlus(u.getAddress().getCurrentHomeAddress()+" "+u.getAddress().getCurrentCity());
                    //String dest = "Targu+Mures";
                    try {
                        url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + dest);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        String line, outputString = "";
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        while ((line = reader.readLine()) != null) {
                            outputString += line;
                        }
                        System.out.println(outputString);
                        DistancePojo capRes = new Gson().fromJson(outputString, DistancePojo.class);
                        String dist = capRes.getRows()[0].getElements()[0].getDistance().getText();

                        String[] array = dist.split(" ");
                        float nr = Float.valueOf(array[0]);
                        results.put(u, nr);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            List<User> closestUsers=results.keySet().stream().filter(p->p.getProfile().getRh().equals(closestDonor.getRh()) && p.getProfile().getBloodType().equals(closestDonor.getBloodType())).collect(Collectors.toList());
            List<User> toRemove = new ArrayList<>();
            for (User us: results.keySet()){
                if (!closestUsers.contains(us)){
                    toRemove.add(us);
                }
            }
            toRemove.forEach(results::remove);


            Map<User, Float> usersSorted = this.getClosestUsers(results);
            donorsResponse = transformDonors(usersSorted);


            closestDonorResponse = ClosestDonorResponse.builder()
                    .isError(false)
                    .message("List of the closest donors")
                    .status("Success")
                    .donors(donorsResponse)
                    .build();

        }else{
            donorsResponse = null;
            closestDonorResponse = ClosestDonorResponse.builder()
                    .isError(true)
                    .message("There are no close donors")
                    .status("Failure")
                    .donors(donorsResponse)
                    .build();
        }
        return closestDonorResponse;
    }

    public String replaceSpaceWithPlus(String initial){
        String fin = initial.replace(" ", "+");
        return fin;
    }

    public Map<User, Float> getClosestUsers(Map<User, Float> users){
        Map<User,Float> result = users.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }

    public List<MatchingDonorDto> transformDonors(Map<User, Float> users){
        List<MatchingDonorDto> result = new ArrayList<>();
        for (Map.Entry<User, Float> entry : users.entrySet())
            result.add(MatchingDonorDto.builder()
                    .firstName(entry.getKey().getProfile().getFirstName())
                    .lastName(entry.getKey().getProfile().getLastName())
                    .currentCity(entry.getKey().getAddress().getCurrentCity())
                    .currentHomeAddress(entry.getKey().getAddress().getCurrentHomeAddress())
                    .phone(entry.getKey().getProfile().getPhone())
                    .email(entry.getKey().getProfile().getEmail())
                    .distance(entry.getValue().toString())
                    .build());
        return result;
        }

}

