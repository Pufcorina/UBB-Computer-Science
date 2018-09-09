package ro.ubb.donation.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoleDto implements Serializable {
    private String description;

    @Override
    public String toString() {
        return "RoleDto{" +
                "description='" + description + '\'' +
                '}';
    }
}
