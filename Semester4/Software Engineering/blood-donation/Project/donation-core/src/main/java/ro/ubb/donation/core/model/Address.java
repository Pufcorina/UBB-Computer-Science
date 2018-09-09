package ro.ubb.donation.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "home_address", nullable = false)
    private String homeAddress;

    @Column(name = "current_home_address", nullable = false)
    private String currentHomeAddress;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "current_city", nullable = false)
    private String currentCity;

    @Column(name = "current_country", nullable = false)
    private String currentCountry;

    @OneToOne(mappedBy = "address", orphanRemoval = true, optional = true)
    private User user;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", homeAddress='" + homeAddress + '\'' +
                ", currentHomeAddress='" + currentHomeAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", currentCity='" + currentCity + '\'' +
                ", currentCountry='" + currentCountry + '\'' +
                '}';
    }

    public static Address getEmptyAddress(){
        return Address.builder().build();
    }
}
