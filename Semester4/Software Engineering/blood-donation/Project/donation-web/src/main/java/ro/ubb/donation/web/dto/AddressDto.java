package ro.ubb.donation.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddressDto implements Serializable {
    private String homeAddress;
    private String currentHomeAddress;
    private String city;
    private String country;
    private String currentCity;
    private String currentCountry;
}
