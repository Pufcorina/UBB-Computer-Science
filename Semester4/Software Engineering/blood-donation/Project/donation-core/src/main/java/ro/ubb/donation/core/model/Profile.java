package ro.ubb.donation.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "blood_type", nullable = false)
    private String bloodType;

    @Column(name = "cnp", nullable = false)
    private String cnp;

    @Column(name = "rh", nullable = false)
    private String rh;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "allergies", nullable = false)
    private String allergies;

    @Column(name = "diseases", nullable = false)
    private String diseases;

    @Column(name = "chronic_illness", nullable = false)
    private String chronicIllness;

    @OneToOne(mappedBy = "profile", orphanRemoval = true, optional = true)
    private User user;

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", cnp='" + cnp + '\'' +
                ", rh='" + rh + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", allergies='" + allergies + '\'' +
                ", diseases='" + diseases + '\'' +
                ", chronicIllness='" + chronicIllness + '\'' +
                '}';
    }

    public static Profile getEmptyProfile(){
        return Profile.builder().build();
    }
}
