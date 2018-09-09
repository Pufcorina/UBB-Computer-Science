package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
