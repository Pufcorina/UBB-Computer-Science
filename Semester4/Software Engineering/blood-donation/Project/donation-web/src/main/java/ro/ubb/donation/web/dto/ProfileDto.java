package ro.ubb.donation.web.dto;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class ProfileDto implements Serializable{
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String cnp;
    private String rh;
    private String email;
    private String phone;
    private String allergies;
    private String diseases;
    private String chronicIllness;

}
