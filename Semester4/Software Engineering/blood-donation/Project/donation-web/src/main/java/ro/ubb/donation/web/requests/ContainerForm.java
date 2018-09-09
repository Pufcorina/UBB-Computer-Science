package ro.ubb.donation.web.requests;

import lombok.*;
import ro.ubb.donation.web.dto.ContainerDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContainerForm {
    private ContainerDto containerDto;
    private int howManyToRemove;
}
