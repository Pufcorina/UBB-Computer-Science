package ro.ubb.lab7.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientDto extends BaseDto {
    private String name;
    private String phoneNumber;
    private String email;
    private Set<Long> movies;

}
