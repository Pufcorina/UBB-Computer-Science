package ro.ubb.donation.core.service;

import ro.ubb.donation.core.model.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(int userId);

    List<User> findAll();

    User updateUser(int userId, String username, String password, boolean logged, Role role, Address address, Profile profile);

    User createUser(String username, String password, boolean logged, Role role, Center center);

    void deleteUser(int userId);

    Optional<User> getUser(String username);

    Optional<User> findByCNP(String cnp);
}
