package ro.ubb.donation.core.service;

import ro.ubb.donation.core.model.Role;
import ro.ubb.donation.core.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Optional<Role> findRole(int roleId);

    List<Role> findAll();

    Role updateRole(int roleId, String description, Set<Integer> users);

    Role createRole(String description);

    void deleteRole(int roleId);

    Optional<Role> getRoleByDescription(String description);

    Optional<String> getRoleDescriptionById(Integer roleId);

}
