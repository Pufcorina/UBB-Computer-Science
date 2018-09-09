package ro.ubb.donation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.donation.core.model.Donation;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.core.service.DonationService;
import ro.ubb.donation.core.service.ResultService;
import ro.ubb.donation.core.service.UserService;
import ro.ubb.donation.web.response.DashboardResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DashboardController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dashboard-info/{username}", method = RequestMethod.GET)
    public DashboardResponse getDashboardInfo(@PathVariable String username) {
        DashboardResponse dashboardResponse = DashboardResponse.builder().build();
        Optional<User> userOptional = userService.getUser(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getProfile() != null)
                dashboardResponse.setFirstName(user.getProfile().getFirstName());
            else
                dashboardResponse.setFirstName("User");
            boolean hasTestResults = false;
            List<Donation> donations = donationService.findAll().stream().filter(d -> d.getUser().getId() == user.getId()).collect(Collectors.toList());
            if (!donations.isEmpty()) {
                Donation donation = donations.get(donations.size() - 1);
                if (donation.getResult() != null) {
                    dashboardResponse.setHasNewTestResults(true);
                    dashboardResponse.setIllnessInfo(donation.getResult().getIllnessInfo());
                    dashboardResponse.setIllnessDiscovered(donation.getResult().isIllnessDiscovered());
                } else {
                    dashboardResponse.setHasNewTestResults(false);
                    dashboardResponse.setIllnessDiscovered(false);
                    dashboardResponse.setIllnessInfo("");
                }
                if (donation.getAppointment_date() != null) {
                    Date appointmentDate = donation.getAppointment_date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(appointmentDate);
                    cal.add(Calendar.MONTH, 2);
                    Date resultDate = cal.getTime();
                    dashboardResponse.setNextPossibleDonationDate(resultDate.toString());
                } else {
                    dashboardResponse.setNextPossibleDonationDate(new Date().toString());
                }
                dashboardResponse.setMessage("The dashboard info was returned");
                dashboardResponse.setStatus("Success");
                dashboardResponse.setError(false);
                return dashboardResponse;
            } else {
                dashboardResponse.setIllnessInfo("");
                dashboardResponse.setIllnessDiscovered(false);
                dashboardResponse.setHasNewTestResults(false);
                dashboardResponse.setStatus("Success");
                dashboardResponse.setError(false);
                dashboardResponse.setNextPossibleDonationDate(new Date().toString());
                dashboardResponse.setMessage("The user doesn't have a profile yet!");
                return dashboardResponse;
            }
        }
        return DashboardResponse.builder()
                .firstName("")
                .illnessDiscovered(false)
                .illnessInfo("")
                .nextPossibleDonationDate("")
                .hasNewTestResults(false)
                .status("Fail")
                .isError(true)
                .message("This user does not exist")
                .build();
    }
}
