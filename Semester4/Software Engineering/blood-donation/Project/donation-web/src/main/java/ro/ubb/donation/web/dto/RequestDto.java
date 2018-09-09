package ro.ubb.donation.web.dto;

import lombok.*;
import ro.ubb.donation.core.model.Center;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestDto implements Serializable {
    private Integer thrombocyteUnits;
    private Integer redCellsUnits;
    private Integer plasmaUnits;
    private int donationCenterId;
    private String locationHospital;
    private String beneficiaryName;
    private boolean activeDonor;
    private String urgencyLevel;
    private String bloodGroup;
    private String rh;
    private String username;
    private String status;

    @Override
    public String toString(){
        return "RequestDto{" +
                ", thrombocytes_units=" + thrombocyteUnits + '\'' +
                ", plasma_units=" + plasmaUnits + '\'' +
                ", id_donation_center=" + donationCenterId + '\'' +
                ", location_hospital=" + locationHospital + '\'' +
                ", beneficiary=" + beneficiaryName + '\'' +
                ", active_donor=" + activeDonor + '\'' +
                ", urgency_level=" + urgencyLevel + '\'' +
                ", blood_group=" + bloodGroup + '\'' +
                ", rh=" + rh + '\'' +
                ", username=" + username + '\'' +
                ", status=" + status;
    }
}
