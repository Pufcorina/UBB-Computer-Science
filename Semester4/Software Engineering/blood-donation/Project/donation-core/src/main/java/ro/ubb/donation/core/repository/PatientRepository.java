package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
