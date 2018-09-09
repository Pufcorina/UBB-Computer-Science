package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
