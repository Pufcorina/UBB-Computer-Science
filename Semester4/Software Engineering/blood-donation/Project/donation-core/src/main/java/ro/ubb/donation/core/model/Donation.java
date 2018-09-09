package ro.ubb.donation.core.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Donation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donation_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private String status;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "created_on")
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Column(name = "cancer_past_5_years")
    private boolean cancerPast5Years;

    @Column(name = "blood_pressure")
    private int bloodPressure;

    @Column(name = "pulse")
    private int pulse;

    @Column(name = "weight")
    private float weight;

    @Column(name = "recent_tattoos")
    private boolean recentTattoos;

    @Column(name = "pregnant_or_menstruating")
    private boolean pregnantOrMenstruating;

    @Column(name = "surgery_past_6_months")
    private boolean surgeryPast6Months;

    @Column(name ="donation_beneficiary")
    private String donationBeneficiary;

    public static Donation getEmptyDonation(){
        return Donation.builder()
                .rejectionReason("")
                .status("")
                .donationBeneficiary("")
                .build();
    }
    @Column(name = "appointment_date")
    @Temporal(TemporalType.DATE)
    private Date appointment_date;

    @OneToOne
    @JoinColumn(name="result_id")
    private Result result;

}
