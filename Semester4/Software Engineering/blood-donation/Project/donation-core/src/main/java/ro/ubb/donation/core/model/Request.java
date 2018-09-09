package ro.ubb.donation.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "thrombocyte_units", nullable = false)
    private int thrombocyteUnits;

    @Column(name = "red_cells_units", nullable = false)
    private int redCellsUnits;

    @Column(name = "plasma_units", nullable = false)
    private int plasmaUnits;

    @ManyToOne
    @JoinColumn(name = "id_donation_center")
    private Center center;

    @Column(name = "location_hospital", nullable = false)
    private String locationHospital;

    @Column(name = "beneficiary_name", nullable = false)
    private String beneficiaryName;

    @Column(name = "active_donor", nullable = false)
    private boolean activeDonor;

    @Column(name = "urgency_level", nullable = false)
    private String urgencyLevel;

    @Column(name = "blood_group", nullable = false)
    private String bloodGroup;

    @Column(name = "rh", nullable = false)
    private String rh;

    @Column(name = "username")
    private String username;

    @Column(name = "status", nullable = false)
    private String status;
}
