package ro.ubb.donation.web.response;


import lombok.*;
import ro.ubb.donation.core.model.Center;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CentersResponse {
    private String status;
    private Boolean isError;
    private List<Center> centers;
    private String message;
}
