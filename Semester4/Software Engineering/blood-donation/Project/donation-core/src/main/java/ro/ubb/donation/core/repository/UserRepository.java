package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
