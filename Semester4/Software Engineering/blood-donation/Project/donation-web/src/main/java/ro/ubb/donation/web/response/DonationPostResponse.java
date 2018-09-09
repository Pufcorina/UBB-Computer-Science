package ro.ubb.donation.web.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DonationPostResponse {
    private String status;
    private Boolean isError;
    private String message;
}
