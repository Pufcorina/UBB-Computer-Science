package ro.ubb.donation.web.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {
    private String username;
    private String email;
    private String password;
    private int centerId;
    private String userType;
}
