package ro.ubb.donation.web.requests;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClosestDonorRequest {
    private String bloodType;
    private String rh;
}
