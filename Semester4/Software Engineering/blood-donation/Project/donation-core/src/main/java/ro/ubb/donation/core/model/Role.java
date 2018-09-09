package ro.ubb.donation.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
        user.setRole(this);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }
}
