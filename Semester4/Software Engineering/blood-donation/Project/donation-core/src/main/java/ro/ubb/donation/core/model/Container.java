package ro.ubb.donation.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Container")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center centerId;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "rh")
    private String rh;

    @Column(name = "component_type", nullable = false)
    private String componentType;

    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", expirationDate='" + expirationDate.getYear() + "-" + expirationDate.getMonth() + "-" + expirationDate.getDay() + '\'' +
                ", centerId='" + centerId.toString() + '\'' +
                ", blood_group=" + bloodGroup +
                ", rh=" + rh +
                ", componentType=" + componentType +
                '}';
    }
}
