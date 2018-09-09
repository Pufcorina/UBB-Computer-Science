package ro.ubb.donation.web.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DonationDto implements Serializable {
    private int donation_id;
    private String status;
    private String rejectionReason;
    private boolean cancerPast5Years;
    private int bloodPressure;
    private int pulse;
    private float weight;
    private boolean recentTattoos;
    private boolean pregnantOrMenstruating;
    private boolean surgeryPast6Months;
    private String donationBeneficiary;

}
