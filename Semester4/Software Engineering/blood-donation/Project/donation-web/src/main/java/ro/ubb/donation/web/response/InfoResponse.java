package ro.ubb.donation.web.response;

import lombok.*;
import ro.ubb.donation.web.dto.AddressDto;
import ro.ubb.donation.web.dto.ProfileDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoResponse {
    private String status;
    private Boolean isError;
    private AddressDto addressDto;
    private ProfileDto profileDto;
    private String message;
}
