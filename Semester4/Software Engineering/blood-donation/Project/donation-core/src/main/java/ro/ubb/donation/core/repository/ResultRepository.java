package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Result;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
