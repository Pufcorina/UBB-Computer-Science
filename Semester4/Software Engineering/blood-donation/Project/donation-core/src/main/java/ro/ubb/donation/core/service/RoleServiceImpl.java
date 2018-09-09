package ro.ubb.donation.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.Role;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.core.repository.RoleRepository;
import ro.ubb.donation.core.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Role> findRole(int roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role updateRole(int roleId, String description, Set<Integer> users) {
        log.trace("updateRole: description={}, users={}", description, users);

        Optional<Role> role = roleRepository.findById(roleId);

        role.ifPresent(r -> {
            r.setDescription(description);

            //initializam cu empty set
            r.setUsers(new HashSet<>());

            //luam din repo userii
            List<User> userList = userRepository.findAllById(users);

            userList.forEach(r::addUser);

        });

        log.trace("updateRole: Role={}", role.get());

        return role.orElse(null);
    }

    @Override
    @Transactional
    public Role createRole(String description) {
        log.trace("createRole: description={}", description);

        Role role = Role.builder()
                .description(description)
                .build();
        role = roleRepository.save(role);

        log.trace("createRole: Role={}", role);

        return role;
    }

    @Override
    @Transactional
    public void deleteRole(int roleId) {
        log.trace("deleteRole: roleId={}", roleId);

        roleRepository.findById(roleId).ifPresent(r -> {
            r.getUsers().forEach(User::removeRole);
        });
        roleRepository.deleteById(roleId);

        log.trace("deleteRole - method end");
    }

    @Override
    public Optional<Role> getRoleByDescription(String description) {

        List<Role> roles = roleRepository.findAll();
        return roles.stream().filter(r -> description.equals(r.getDescription())).findFirst();
    }

    @Override
    public Optional<String> getRoleDescriptionById(Integer roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        return role.map(Role::getDescription);
    }
}
