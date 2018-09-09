package ro.ubb.donation.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.model.Request;
import ro.ubb.donation.core.repository.RequestRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private static final Logger log = LoggerFactory.getLogger( RequestServiceImpl.class );

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Optional<Request> findRequest(int requestId) {
        return requestRepository.findById( requestId );
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> findAllByCenterId(int centerId){
        return requestRepository.findAll().stream().filter(r->(r.getCenter().getId() == centerId && r.getStatus().equals("PENDING"))).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Request updateRequest(int requestId, Integer thrombocyteUnits, Integer redCellsunits, Integer plasmaUnits, Center donationCenter, String locationHospital, String beneficiaryName, boolean activeDonor, String urgencyLevel, String bloodGroup, String rh, String username, String status) {
        log.trace( "updateRequest: requestId={}, thrombocyteUnits={}, redCellsunits={}, plasmaUnits={}, donationCenterId={}, locationHospital={}, beneficiaryName={}, activeDonor={}, urgencyLevel={}, bloodGroup={}, rh={}, username={}, status={}", requestId, thrombocyteUnits, redCellsunits, plasmaUnits, donationCenter, locationHospital, beneficiaryName, activeDonor, urgencyLevel, bloodGroup, rh, username, status );

        Optional<Request> request = requestRepository.findById( requestId );
        request.ifPresent( r -> {
            r.setThrombocyteUnits( thrombocyteUnits );
            r.setRedCellsUnits( redCellsunits );
            r.setPlasmaUnits( plasmaUnits );
            r.setCenter( donationCenter );
            r.setLocationHospital( locationHospital );
            r.setBeneficiaryName( beneficiaryName );
            r.setActiveDonor( activeDonor );
            r.setUrgencyLevel( urgencyLevel );
            r.setBloodGroup( bloodGroup );
            r.setRh( rh );
            r.setUsername( username );
            r.setStatus( status );
        } );

        log.trace( "updateRequest={}" );
        return request.orElse( null );
    }

    @Override
    @Transactional
    public Request createRequest(Integer thrombocyteUnits, Integer redCellsunits, Integer plasmaUnits, Center donationCenter, String locationHospital, String beneficiaryName, boolean activeDonor, String urgencyLevel, String bloodGroup, String rh, String username, String status) {
        log.trace( "createRequest: thrombocyteUnits={}, redCellsunits={}, plasmaUnits={}, donationCenterId={}, locationHospital={}, beneficiaryName={}, activeDonor={}, urgencyLevel={}, bloodGroup={}, rh={}, username={}, status={}", thrombocyteUnits, redCellsunits, plasmaUnits, donationCenter, locationHospital, beneficiaryName, activeDonor, urgencyLevel, bloodGroup, rh, username, status );

        Request request = Request.builder()
                .thrombocyteUnits( thrombocyteUnits )
                .redCellsUnits( redCellsunits )
                .plasmaUnits( plasmaUnits )
                .center( donationCenter )
                .locationHospital( locationHospital )
                .beneficiaryName( beneficiaryName )
                .activeDonor( activeDonor )
                .urgencyLevel( urgencyLevel )
                .bloodGroup( bloodGroup )
                .rh( rh )
                .username( username )
                .status( status )
                .build();

        requestRepository.save( request );

        log.trace( "createRequest={}" );
        return request;
    }

    @Override
    @Transactional
    public void deleteRequest(int requestId) {
        log.trace( "deleteRequest: requestId={}", requestId );

        Optional<Request> request = requestRepository.findById( requestId );
        request.ifPresent( r -> requestRepository.delete( r ) );
    }

    @Override
    public List<Request> getRequestsByDoctor(String username) {
        log.trace( "getRequestsByDoctor: username = {}", username );

        return requestRepository.findAll().stream().filter( r-> username.equals( r.getUsername() ) ).collect(Collectors.toList());
    }
}
