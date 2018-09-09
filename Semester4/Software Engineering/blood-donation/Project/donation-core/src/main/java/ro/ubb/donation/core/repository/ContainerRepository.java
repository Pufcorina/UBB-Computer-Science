package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Container;

public interface ContainerRepository extends JpaRepository<Container, Integer> {
}
