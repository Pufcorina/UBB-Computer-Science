package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
}
