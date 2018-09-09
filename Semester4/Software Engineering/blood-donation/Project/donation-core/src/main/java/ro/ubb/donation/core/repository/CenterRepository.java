package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Center;

public interface CenterRepository extends JpaRepository<Center, Integer> {
}
