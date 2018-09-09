package ro.ubb.donation.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ResultDto {
    private String appointmentDate;
    private boolean illnessDiscovered;
    private String illnessInfo;
    private String result_pdf;
}
