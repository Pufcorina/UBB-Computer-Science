package ro.ubb.donation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.donation.core.model.Address;
import ro.ubb.donation.core.model.Donation;
import ro.ubb.donation.core.model.Profile;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.core.service.DonationService;
import ro.ubb.donation.core.service.UserService;
import ro.ubb.donation.web.converter.AddressConverter;
import ro.ubb.donation.web.converter.DonationConverter;
import ro.ubb.donation.web.converter.ProfileConverter;
import ro.ubb.donation.web.dto.DonationDto;
import ro.ubb.donation.web.dto.DonationFormDto;
import ro.ubb.donation.web.requests.DonationFormPost;
import ro.ubb.donation.web.requests.DonationFormRequest;
import ro.ubb.donation.web.response.DonationFormsResponse;
import ro.ubb.donation.web.response.DonationGetResponse;
import ro.ubb.donation.web.response.DonationPostResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DonationController {
    @Autowired
    private DonationService donationService;

    @Autowired
    private DonationConverter donationConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileConverter profileConverter;

    @Autowired
    private AddressConverter addressConverter;

    @RequestMapping(value = "/donation-forms", method = RequestMethod.POST)
    public DonationPostResponse createDonation(@RequestBody DonationFormPost donationForm){
        Optional<User> userOptional = this.userService.getUser(donationForm.getUsername());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            DonationDto donation = donationForm.getDonationDto();

            this.donationService.createDonation(user,donation.getStatus(),donation.isCancerPast5Years()
            ,donation.isRecentTattoos(),donation.isPregnantOrMenstruating(),donation.isSurgeryPast6Months(),donation.getPulse(),donation.getBloodPressure(),
                    donation.getWeight(),donation.getDonationBeneficiary());

            return DonationPostResponse.builder()
                    .isError(false)
                    .status("Success")
                    .message("Donation in PENDING status was successfully created").build();
        }
        return DonationPostResponse.builder().isError(true).message("The user with this username doesn't exist!").status("Error").build();
    }

    @RequestMapping(value = "/donation-forms/{donationId}", method = RequestMethod.POST)
    public DonationGetResponse updateDonation(@RequestBody DonationFormPost donationFormPost,@PathVariable int donationId){
        Optional<User> userOptional = this.userService.getUser(donationFormPost.getUsername());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            DonationDto donation = donationFormPost.getDonationDto();

            this.donationService.updateDonation(donationId,donation.getStatus(),donation.getRejectionReason(),donation.isCancerPast5Years()
                    ,donation.isRecentTattoos(),donation.isPregnantOrMenstruating(),donation.isSurgeryPast6Months(),donation.getPulse(),donation.getBloodPressure(),
                    donation.getWeight(),donation.getDonationBeneficiary());

            return this.getDonationFormInfo(donationFormPost.getUsername());
        }
        return DonationGetResponse.builder()
                .status("Error")
                .message("The user doesn't exist")
                .addressDto(this.addressConverter.convertModelToDto(Address.getEmptyAddress()))
                .profileDto(this.profileConverter.convertModelToDto(Profile.getEmptyProfile()))
                .donationDto(this.donationConverter.convertModelToDto(Donation.getEmptyDonation())).build();
    }

    @RequestMapping(value = "/donation-forms/{username}", method = RequestMethod.GET)
    public DonationGetResponse getDonationFormInfo(
            @PathVariable String username){
        Optional<User> userOptional = this.userService.getUser(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            Donation donation;
            Profile profile = user.getProfile();
            Address address = user.getAddress();
            Optional<Donation> optionalDonation = this.donationService.findDonationByUser(user);
            if(profile == null)
                profile = Profile.getEmptyProfile();
            if (address == null)
                address = Address.getEmptyAddress();
            donation = optionalDonation.orElseGet(Donation::getEmptyDonation);
            return DonationGetResponse.builder()
                    .isError(false)
                    .status("Success")
                    .message("")
                    .addressDto(this.addressConverter.convertModelToDto(address))
                    .profileDto(this.profileConverter.convertModelToDto(profile))
                    .donationDto(this.donationConverter.convertModelToDto(donation))
                    .build();
        }
        else
            return DonationGetResponse.builder()
                    .status("Error")
                    .message("The user doesn't exist")
                    .addressDto(this.addressConverter.convertModelToDto(Address.getEmptyAddress()))
                    .profileDto(this.profileConverter.convertModelToDto(Profile.getEmptyProfile()))
                    .donationDto(this.donationConverter.convertModelToDto(Donation.getEmptyDonation())).build();
    }


    @RequestMapping(value = "/received-forms", method = RequestMethod.GET)
    public DonationFormsResponse getAllDonationForms() {

        List<Donation> donationForms = donationService.findAllByStatus("PENDING");
        List<DonationFormDto> forms = new ArrayList<>();
        User user;
        Profile profile;
        DonationFormDto donationFormDto;
        for(Donation d: donationForms){
            user = d.getUser();
            profile = user.getProfile();
            donationFormDto = DonationFormDto.builder()
                    .donationDto(donationConverter.convertModelToDto(d))
                    .profileDto(profileConverter.convertModelToDto(profile))
                    .build();
            forms.add(donationFormDto);
        }


        return DonationFormsResponse.builder()
                .forms( forms )
                .isError( false )
                .status("Success")
                .message("These are all requests!")
                .build();
    }

    @RequestMapping(value = "/donation-forms/approve", method = RequestMethod.PUT)
    public DonationPostResponse updateDonationApprove(@RequestBody DonationFormRequest donationFormRequest){
        try{
            donationService.updateDonationStatus(donationFormRequest.getDonation_id(),"APPROVED","",donationFormRequest.getAppointmentDate());
        }
        catch (Exception ex){
            return DonationPostResponse.builder().status("Error").message("Error parsing date").isError(true).build();
        }
        return DonationPostResponse.builder().status("Success").message("The Donation was successfully updated").isError(false).build();
    }

    @RequestMapping(value = "/donation-forms/reject", method = RequestMethod.PUT)
    public DonationPostResponse updateDonationReject(@RequestBody DonationFormRequest donationFormRequest){
        try{
            donationService.updateDonationStatus(donationFormRequest.getDonation_id(),"REJECTED",donationFormRequest.getRejectionReason(),"");
        }
        catch (Exception ex){
            return DonationPostResponse.builder().status("Error").message("Error parsing date").isError(true).build();
        }
        return DonationPostResponse.builder().status("Success").message("The Donation was successfully updated").isError(false).build();
    }
}
