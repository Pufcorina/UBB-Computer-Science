package ro.ubb.donation.web.dto;

import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto implements Serializable{
    private String username;
    private String password;
    private boolean logged;

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", logged=" + logged +
                '}';
    }
}
