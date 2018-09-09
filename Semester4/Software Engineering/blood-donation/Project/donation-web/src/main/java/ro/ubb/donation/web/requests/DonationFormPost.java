package ro.ubb.donation.web.requests;

import lombok.*;
import ro.ubb.donation.web.dto.DonationDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonationFormPost {
    private String username;
    private DonationDto donationDto;
}
