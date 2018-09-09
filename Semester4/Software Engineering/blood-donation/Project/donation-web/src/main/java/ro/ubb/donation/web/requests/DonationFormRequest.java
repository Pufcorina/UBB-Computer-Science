package ro.ubb.donation.web.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonationFormRequest {
    private int donation_id;
    private String appointmentDate;
    private String rejectionReason;
    private String status;
}
