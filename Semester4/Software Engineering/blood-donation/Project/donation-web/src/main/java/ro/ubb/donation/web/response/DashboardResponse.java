package ro.ubb.donation.web.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DashboardResponse {
    private String status;
    private boolean isError;
    private String message;
    private String firstName;
    private boolean illnessDiscovered;
    private String illnessInfo;
    private String nextPossibleDonationDate;
    private boolean hasNewTestResults;
}
