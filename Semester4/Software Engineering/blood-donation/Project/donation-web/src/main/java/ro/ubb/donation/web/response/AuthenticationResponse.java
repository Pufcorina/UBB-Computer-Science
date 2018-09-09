package ro.ubb.donation.web.response;

import lombok.*;
import ro.ubb.donation.web.dto.UserDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String status;
    private Boolean isError;
    private UserDto userDto;
    private String role;
    private String message;
    private int centerId;
}
