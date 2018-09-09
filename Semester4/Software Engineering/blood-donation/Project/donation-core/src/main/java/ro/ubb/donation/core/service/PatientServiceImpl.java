package ro.ubb.donation.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.Patient;
import ro.ubb.donation.core.repository.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientServiceImpl implements PatientService {
    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Optional<Patient> findPatient(int patientId) {
        log.trace("findPatient:patientId={}", patientId);
        Optional<Patient> patientOptional= patientRepository.findById(patientId);
        log.trace("findPatient: patientOptional={}", patientOptional);
        return patientOptional;
    }

    @Override
    public List<Patient> findAll() {
        log.trace("findAll --- method entered");

        List<Patient> patients = patientRepository.findAll();

        log.trace("findAll: patients={}", patients);

        return patients;
    }

    @Override
    @Transactional
    public Patient updatePatient(int patientId, String fullName, int thrombocyteNeed, int redCellsNeed, int plasmaNeed, int noOfDonations) {
        log.trace("updatePatient: fullName={}, thrombocyteNeed={}, redCellsNeed={}, plasmaNeed={}, noOfDonations={}", fullName, thrombocyteNeed, redCellsNeed, plasmaNeed, noOfDonations);

        Optional<Patient> patient = patientRepository.findById(patientId);

        patient.ifPresent( p -> {
            p.setFullName( fullName );
            p.setThrombocyteNeed( thrombocyteNeed );
            p.setPlasmaNeed( plasmaNeed );
            p.setRedCellsNeed( redCellsNeed );
            p.setNoOfDonations( noOfDonations );
        });

        log.trace("updatePatient: patient={}", patient.get());

        return patient.orElse(null);
    }

    @Override
    @Transactional
    public Patient createPatient(String fullName, int thrombocyteNeed, int redCellsNeed, int plasmaNeed, int noOfDonations) {
        log.trace("updatePatient: fullName={}, thrombocyteNeed={}, redCellsNeed={}, plasmaNeed={}, noOfDonations={}", fullName, thrombocyteNeed, redCellsNeed, plasmaNeed, noOfDonations);

        Patient patient = Patient.builder()
                .fullName( fullName )
                .thrombocyteNeed( thrombocyteNeed )
                .redCellsNeed( redCellsNeed )
                .plasmaNeed( plasmaNeed )
                .noOfDonations( noOfDonations )
                .build();

        patient = patientRepository.save( patient );

        log.trace("createPatient: patient={}", patient);

        return patient;
    }

    @Override
    @Transactional
    public void deletePatient(int patientId) {
        log.trace("deletePatient: patientId={}", patientId);

        patientRepository.deleteById(patientId);

        log.trace("deletePatient - method end");
    }

    @Override
    public Optional<Patient> getPatient(String fullName) {
        List<Patient> patients = patientRepository.findAll().stream()
                .filter( p -> fullName.equals( p.getFullName() ) ).collect(Collectors.toList());

        if(patients.isEmpty())
            return Optional.empty();

        return Optional.ofNullable( patients.get( 0 ) );
    }
}
