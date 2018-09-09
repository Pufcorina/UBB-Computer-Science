package ro.ubb.donation.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Patient")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "thrombocyte_need", nullable = false)
    private int thrombocyteNeed;

    @Column(name = "red_cells_need", nullable = false)
    private int redCellsNeed;

    @Column(name = "plasma_need", nullable = false)
    private int plasmaNeed;

    @Column(name = "no_of_donations", nullable = false)
    private int noOfDonations;
}
