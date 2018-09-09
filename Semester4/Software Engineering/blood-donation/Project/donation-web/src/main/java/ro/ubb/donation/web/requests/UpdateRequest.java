package ro.ubb.donation.web.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
    private int requestID;
    private String status;
}
