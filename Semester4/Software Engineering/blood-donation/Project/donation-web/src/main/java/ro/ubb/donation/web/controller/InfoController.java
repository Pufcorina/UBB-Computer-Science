package ro.ubb.donation.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.donation.core.model.Address;
import ro.ubb.donation.core.model.Profile;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.core.service.AddressService;
import ro.ubb.donation.core.service.ProfileService;
import ro.ubb.donation.core.service.UserService;
import ro.ubb.donation.web.converter.AddressConverter;
import ro.ubb.donation.web.converter.ProfileConverter;
import ro.ubb.donation.web.dto.AddressDto;
import ro.ubb.donation.web.dto.ProfileDto;
import ro.ubb.donation.web.requests.InfoRequest;

import ro.ubb.donation.web.response.InfoResponse;

import java.util.Optional;

@RestController
public class InfoController {
    private static final Logger log = LoggerFactory.getLogger(InfoController.class);
    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileConverter profileConverter;


    @RequestMapping(value = "/my-info/{username}", method = RequestMethod.GET)
    public InfoResponse getUserInfo(@PathVariable String username){

        System.out.println("A GET request was made on /my-info/"+username);
        Optional<User> userOptional = userService.getUser(username);
        boolean isError = false;
        if(userOptional.isPresent()){
            User user = userOptional.get();
            Address address = user.getAddress();
            Profile profile = user.getProfile();

            AddressDto addressDto;
            ProfileDto profileDto;
            if (address==null){
                addressDto = null;
                isError = true;
            }else{
                addressDto = addressConverter.convertModelToDto(address);
            }
            if(profile == null){
                profileDto = null;
                isError = true;
            }else{
                profileDto = profileConverter.convertModelToDto(profile);
            }

            return InfoResponse.builder()
                    .status("Success")
                    .message("Data passed")
                    .isError(isError)
                    .addressDto(addressDto)
                    .profileDto(profileDto)
                    .build();
        }
        return InfoResponse.builder()
                .status("Error")
                .message("Empty profile or user doesn't exist!")
                .isError(true)
                .addressDto(null)
                .profileDto(null)
                .build();
    }


    @RequestMapping(value = "/my-info", method = RequestMethod.POST)
    public InfoResponse createUpdateInfo(@RequestBody final InfoRequest infoRequest){
        System.out.println("A POST request was made on /my-info" + infoRequest.toString());

        Optional<User> userOptional = userService.getUser(infoRequest.getUsername());
        User user = userOptional.get();
        Address address = userOptional.get().getAddress();
        Profile profile = userOptional.get().getProfile();

        if (address!=null || profile!=null){
            Address a= addressService.updateAddress(address.getId(), infoRequest.getAddressDto().getHomeAddress(), infoRequest.getAddressDto().getCurrentHomeAddress(), infoRequest.getAddressDto().getCity(), infoRequest.getAddressDto().getCountry(), infoRequest.getAddressDto().getCurrentCity(), infoRequest.getAddressDto().getCurrentCountry());
            Profile p = profileService.updateProfile(profile.getId(), infoRequest.getProfileDto().getFirstName(), infoRequest.getProfileDto().getLastName(), infoRequest.getProfileDto().getDateOfBirth(), infoRequest.getProfileDto().getGender(), infoRequest.getProfileDto().getBloodGroup(),infoRequest.getProfileDto().getCnp(), infoRequest.getProfileDto().getRh(), infoRequest.getProfileDto().getEmail(), infoRequest.getProfileDto().getPhone(), infoRequest.getProfileDto().getAllergies(), infoRequest.getProfileDto().getDiseases(),infoRequest.getProfileDto().getChronicIllness());
            return InfoResponse.builder()
                    .status("success")
                    .profileDto(profileConverter.convertModelToDto(p))
                    .addressDto(addressConverter.convertModelToDto(a))
                    .isError(false)
                    .message("Data updated")
                    .build();
        }
        else {
            Address address1 = addressService.createAddress(infoRequest.getAddressDto().getHomeAddress(), infoRequest.getAddressDto().getCurrentHomeAddress(), infoRequest.getAddressDto().getCity(), infoRequest.getAddressDto().getCountry(), infoRequest.getAddressDto().getCurrentCity(), infoRequest.getAddressDto().getCurrentCountry());
            Profile profile1 = profileService.createProfile(infoRequest.getProfileDto().getFirstName(), infoRequest.getProfileDto().getLastName(), infoRequest.getProfileDto().getDateOfBirth(), infoRequest.getProfileDto().getGender(), infoRequest.getProfileDto().getBloodGroup(),infoRequest.getProfileDto().getCnp(), infoRequest.getProfileDto().getRh(), infoRequest.getProfileDto().getEmail(), infoRequest.getProfileDto().getPhone(), infoRequest.getProfileDto().getAllergies(), infoRequest.getProfileDto().getDiseases(),infoRequest.getProfileDto().getChronicIllness());
            User u = userService.updateUser(user.getId(), user.getUsername(), user.getPassword(), user.isLogged(), user.getRole(), address1, profile1);
            log.trace("createUser: result={}", u);
            return InfoResponse.builder()
                    .status("success")
                    .profileDto(profileConverter.convertModelToDto(profile1))
                    .addressDto(addressConverter.convertModelToDto(address1))
                    .isError(false)
                    .message("Info regarding this patient is stored")
                    .build();
        }
    }
}
