package ro.ubb.donation.core.service;

import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.model.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    Optional<Request> findRequest(int requestId);
    
    List<Request> findAll();

    List<Request> findAllByCenterId(int centerid);
    
    Request updateRequest(int requestId, Integer thrombocyteUnits, Integer redCellsunits, Integer plasmaUnits, Center donationCenter,
                          String locationHospital, String beneficiaryName, boolean activeDonor, String urgencyLevel,
                          String bloodGroup, String rh, String username, String status);

    Request createRequest(Integer thrombocyteUnits, Integer redCellsunits, Integer plasmaUnits, Center donationCenter,
                          String locationHospital, String beneficiaryName, boolean activeDonor, String urgencyLevel,
                          String bloodGroup, String rh, String username, String status);

    void deleteRequest(int requestId);

    List<Request> getRequestsByDoctor(String username);
}
