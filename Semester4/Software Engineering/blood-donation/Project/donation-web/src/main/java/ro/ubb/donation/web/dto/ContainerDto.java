package ro.ubb.donation.web.dto;

import lombok.*;
import ro.ubb.donation.core.model.Center;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ContainerDto {
    private String expirationDate;
    private int centerId;
    private String bloodGroup;
    private String rh;
    private String componentType;
}
