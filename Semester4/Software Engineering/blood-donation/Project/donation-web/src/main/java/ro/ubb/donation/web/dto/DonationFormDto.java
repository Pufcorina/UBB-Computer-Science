package ro.ubb.donation.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class DonationFormDto {
    private DonationDto donationDto;
    private ProfileDto profileDto;
}
