package ro.ubb.donation.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class MatchingDonorDto {
    private String firstName;
    private String lastName;
    private String currentHomeAddress;
    private String currentCity;
    private String phone;
    private String email;
    private String distance;
}
