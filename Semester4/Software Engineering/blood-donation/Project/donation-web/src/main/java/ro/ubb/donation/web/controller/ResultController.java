package ro.ubb.donation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.donation.core.model.Donation;
import ro.ubb.donation.core.model.Result;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.core.service.DonationService;
import ro.ubb.donation.core.service.ResultService;
import ro.ubb.donation.core.service.UserService;
import ro.ubb.donation.web.converter.ResultConverter;
import ro.ubb.donation.web.dto.ResultDto;
import ro.ubb.donation.web.requests.ResultForm;
import ro.ubb.donation.web.response.InfoResponse;
import ro.ubb.donation.web.response.ResultsResponse;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ResultController {
    @Autowired
    private ResultConverter resultConverter;

    @Autowired
    private DonationService donationService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/results/{username}", method = RequestMethod.GET)
    public ResultsResponse getAllResultsForUsername(@PathVariable String username){
        List<Donation> donations = donationService.findDonationByUsername( username );
        donations = donations.stream().filter( d -> d.getResult() != null ).collect(Collectors.toList());
        List<Result> results = new ArrayList<>( );
        donations.forEach( d -> results.add( d.getResult() ) );

        return ResultsResponse.builder()
                .isError(false)
                .message("These are the results!")
                .status("success")
                .results(new HashSet<>( resultConverter.convertModelsToDtos( results )))
                .build();
    }

    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public InfoResponse createResult(@RequestBody ResultForm resultForm)
    {
        Optional<User> userOptional = this.userService.findByCNP(resultForm.getCnp());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            Optional<Donation> donationOptional = this.donationService.findDonationByUser(user);
            if(donationOptional.isPresent()){
                Donation donation = donationOptional.get();
                Result result = this.resultService.createResult(resultForm.isIllnessDiscovered(), resultForm.getIllnessInfo(),resultForm.getUploadedFileUrl());

                this.donationService.updateDonationResult(donation.getDonation_id(), result);
                return InfoResponse.builder().isError(false).message("Result was added successfully").status("success").build();
            }
            else return InfoResponse.builder().isError(true).message("There is no donation for this user!").status("error").build();
        }
        else return InfoResponse.builder().isError(true).message("There is no user with that CNP").status("error").build();
    }
}
