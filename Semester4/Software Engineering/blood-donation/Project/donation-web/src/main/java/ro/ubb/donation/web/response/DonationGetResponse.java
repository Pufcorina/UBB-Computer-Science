package ro.ubb.donation.web.response;

import lombok.*;
import ro.ubb.donation.web.dto.AddressDto;
import ro.ubb.donation.web.dto.DonationDto;
import ro.ubb.donation.web.dto.ProfileDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DonationGetResponse {
    private String status;
    private boolean isError;
    private String message;
    private ProfileDto profileDto;
    private AddressDto addressDto;
    private DonationDto donationDto;
}
