package ro.ubb.donation.web.response;

import lombok.*;
import ro.ubb.donation.core.model.Container;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContainerResponse {
    private String status;
    private Boolean isError;
    private String message;
}
