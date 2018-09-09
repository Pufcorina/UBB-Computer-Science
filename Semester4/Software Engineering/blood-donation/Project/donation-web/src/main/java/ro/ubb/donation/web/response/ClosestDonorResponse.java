package ro.ubb.donation.web.response;

import lombok.*;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.web.dto.MatchingDonorDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClosestDonorResponse {
    private String status;
    private boolean isError;
    private String message;

    private List<MatchingDonorDto> donors;
}

