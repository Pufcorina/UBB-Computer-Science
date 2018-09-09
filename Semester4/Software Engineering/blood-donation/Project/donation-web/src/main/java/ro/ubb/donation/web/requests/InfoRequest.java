package ro.ubb.donation.web.requests;

import lombok.*;
import ro.ubb.donation.web.dto.AddressDto;
import ro.ubb.donation.web.dto.ProfileDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InfoRequest {
    private AddressDto addressDto;
    private ProfileDto profileDto;
    private String username;
}
