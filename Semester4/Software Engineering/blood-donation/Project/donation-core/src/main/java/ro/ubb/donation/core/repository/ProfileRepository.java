package ro.ubb.donation.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.donation.core.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
