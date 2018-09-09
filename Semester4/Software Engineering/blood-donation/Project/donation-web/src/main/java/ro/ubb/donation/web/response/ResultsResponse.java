package ro.ubb.donation.web.response;


import lombok.*;
import ro.ubb.donation.web.dto.ResultDto;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResultsResponse {
    private String status;
    private Boolean isError;
    private String message;
    public Set<ResultDto> results;
}
