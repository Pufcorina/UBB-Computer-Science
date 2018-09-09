package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
