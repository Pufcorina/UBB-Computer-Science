package ro.ubb.donation.web.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultForm {
    private String uploadedFileUrl;
    private String cnp;
    private boolean illnessDiscovered;
    private String illnessInfo;
}
