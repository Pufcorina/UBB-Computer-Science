package ro.ubb.donation.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Result")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "illness_discovered")
    private boolean illnessDiscovered;

    @Column(name = "illness_info")
    private String illnessInfo;

    @Column(name = "result_pdf")
    private String resultPdf;

    @OneToOne(mappedBy = "result", orphanRemoval = true, optional = true)
    private Donation donation;
}
