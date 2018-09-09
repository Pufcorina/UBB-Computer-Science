package ro.ubb.donation.core.service;

import ro.ubb.donation.core.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Optional<Patient> findPatient(int patientId);

    List<Patient> findAll();

    Patient updatePatient(int patientId, String fullName, int thrombocyteNeed, int redCellsNeed, int plasmaNeed, int noOfDonations);

    Patient createPatient(String fullName, int thrombocyteNeed, int redCellsNeed, int plasmaNeed, int noOfDonations);

    void deletePatient(int patientId);

    Optional<Patient> getPatient(String fullName);
}
