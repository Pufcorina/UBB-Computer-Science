package ro.ubb.donation.web.response;

import lombok.*;
import ro.ubb.donation.web.dto.DonationFormDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DonationFormsResponse {
    private List<DonationFormDto> forms;
    private String status;
    private Boolean isError;
    private String message;
}
