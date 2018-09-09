package ro.ubb.donation.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.*;
import ro.ubb.donation.core.repository.RoleRepository;
import ro.ubb.donation.core.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<User> findUser(int userId) {
        log.trace("findUser:userId={}", userId);
        Optional<User> userOptional= userRepository.findById(userId);
        log.trace("findUser: userOptional={}", userOptional);
        return userOptional;
    }

    @Override
    public List<User> findAll() {
        log.trace("findAll --- method entered");

        List<User> users = userRepository.findAll();

        log.trace("findAll: users={}", users);

        return users;
    }

    @Override
    @Transactional
    public User updateUser(int userId, String username, String password, boolean logged, Role role, Address address, Profile profile) {
        log.trace("updateUser: username={}, password={}, address, profile", username, password, logged, role, address, profile);

        Optional<User> user = userRepository.findById(userId);

        user.ifPresent(u -> {
            u.setUsername(username);
            u.setPassword(password);
            u.setLogged(logged);
            u.setRole(role);
            u.setAddress(address);
            u.setProfile(profile);
        });

        log.trace("updateUser: user={}", user.get());

        return user.orElse(null);
    }

    @Override
    @Transactional
    public User createUser(String username, String password, boolean logged, Role role, Center center) {
        log.trace("createUser: username={}, password={}, logged={}, role={}",
                username, password, logged, role);

        User user = User.builder()
                .username(username)
                .password(AuthManager.encrypt( password ))
                .logged(logged)
                .role(role)
                .center(center)
                .build();
        user = userRepository.save(user);
        log.trace("createUser: user={}", user);

        return user;
    }

    @Override
    @Transactional
    public void deleteUser(int userId) {
        log.trace("deleteUser: userId={}", userId);

        userRepository.deleteById(userId);

        log.trace("deleteUser - method end");
    }

    public Optional<User> getUser(String username){
        List<User> users = userRepository.findAll();
        users = users.stream()
                .filter(u -> username.equals(u.getUsername())).collect(Collectors.toList());

        if(users.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(users.get(0));
    }

    @Override
    public Optional<User> findByCNP(String cnp){
        List<User> users = userRepository.findAll().stream().filter(u->u.getProfile()!=null).collect(Collectors.toList());
        return users.stream().filter(u-> u.getProfile().getCnp().equals(cnp)).findFirst();
    }

}
