package ro.ubb.donation.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.donation.core.model.AuthManager;
import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.model.Role;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.core.service.CenterService;
import ro.ubb.donation.core.service.RoleService;
import ro.ubb.donation.core.service.UserService;
import ro.ubb.donation.web.converter.UserConverter;
import ro.ubb.donation.web.dto.UserDto;
import ro.ubb.donation.web.requests.LoginForm;
import ro.ubb.donation.web.requests.RegistrationForm;
import ro.ubb.donation.web.response.AuthenticationResponse;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CenterService centerService;


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Set<UserDto> getUsers() {
        System.out.println("A GET request was made on /users");

        log.trace("getUsers --- method entered");

        List<User> users = userService.findAll();

        Set<UserDto> userDtos = new HashSet<>(userConverter.convertModelsToDtos(users));

        log.trace("getDisciplines: disciplineDtos={}", userDtos);

        return userDtos;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationResponse loginUser(
            @RequestBody LoginForm loginForm) {
        System.out.println("A POST request was made on /login");

        Optional<User> userOptional = userService.getUser(loginForm.getUsername());

        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getPassword().equals(AuthManager.encrypt( loginForm.getPassword() ) )) {
            //if(user.getPassword().equals(loginForm.getPassword())) {
                Optional<String> role = roleService.getRoleDescriptionById(user.getRole().getId());

                AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                        .status("Success")
                        .userDto(null)
                        .message("User found!")
                        .role(role.orElse(null))
                        .isError(false)
                        .build();
                return authenticationResponse;
            }
        }
        return AuthenticationResponse.builder()
                .status("error")
                .message("No registered user with this username and password")
                .role("")
                .userDto(null)
                .isError(true)
                .build();
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public AuthenticationResponse logAndGetUser(
            @PathVariable String username) {

        System.out.println("A GET request was made on /users/"+username);

        Optional<User> user = userService.getUser(username);

        if(user.isPresent()){
            User updatedUser = userService.updateUser(user.get().getId(),user.get().getUsername(),
                    user.get().getPassword(),true,user.get().getRole(), user.get().getAddress(), user.get().getProfile());
            int centerId;
            if (user.get().getCenter() != null)
                centerId = user.get().getCenter().getId();
            else
                centerId = 0;

            return AuthenticationResponse.builder()
                    .status("Success")
                    .role(roleService.getRoleDescriptionById(user.get().getRole().getId()).orElse(null))
                    .centerId(centerId)
                    .message("The user is now logged")
                    .isError(false)
                    .userDto(userConverter.convertModelToDto(updatedUser))
                    .build();
        }

        return AuthenticationResponse.builder()
                .status("failure")
                .role("")
                .userDto(null)
                .message("The user doesn't exist")
                .isError(true)
                .build();

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public AuthenticationResponse logoutUser(
            @RequestBody LoginForm loginForm) {

        System.out.println("A POST request was made on /logout");

        Optional<User> user = userService.getUser(loginForm.getUsername());

        if(user.isPresent()){
            User updatedUser = userService.updateUser(user.get().getId(),user.get().getUsername(),
                    user.get().getPassword(),false,user.get().getRole(), user.get().getAddress(), user.get().getProfile());

            return AuthenticationResponse.builder()
                    .status("Success")
                    .message("The user is now logged out")
                    .isError(false)
                    .build();
        }

        return AuthenticationResponse.builder()
                .status("failure")
                .role("")
                .userDto(null)
                .message("The user doesn't exist")
                .isError(true)
                .build();

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public AuthenticationResponse createUser(
            @RequestBody final LoginForm loginForm) {

        System.out.println("A POST request was made on /users");

        Optional<User> userOptional = userService.getUser(loginForm.getUsername());

        if(userOptional.isPresent()) {
            return AuthenticationResponse.builder()
                    .status("failure")
                    .userDto(null)
                    .message("There already exists a user with this username.")
                    .role("")
                    .build();
        }

        Optional<Role> role = roleService.getRoleByDescription("Donor");
        User user = userService.createUser(loginForm.getUsername(), loginForm.getPassword(), false, role.orElse(null), null);

        UserDto userDto1 = userConverter.convertModelToDto(user);

        log.trace("createUser: result={}", userDto1);

        return AuthenticationResponse.builder()
                .status("success")
                .role("Donor")
                .userDto(userDto1)
                .message("The user was successfully created")
                .build();
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public AuthenticationResponse updateUserState(
        @RequestBody UserDto userDto){

        System.out.println("A PUT request was made on /user");

        Optional<User> user = userService.getUser(userDto.getUsername());

        if(user.isPresent()){
            User updatedUser = userService.updateUser(user.get().getId(),userDto.getUsername(),
                    userDto.getPassword(),userDto.isLogged(),user.get().getRole(), user.get().getAddress(), user.get().getProfile());

            return AuthenticationResponse.builder()
                    .status("Success")
                    .role(roleService.getRoleDescriptionById(user.get().getRole().getId()).orElse(null))
                    .message("The user was successfully updated")
                    .userDto(userConverter.convertModelToDto(updatedUser))
                    .build();
        }

        return AuthenticationResponse.builder()
                .status("failure")
                .role("")
                .userDto(null)
                .message("The user doesn't exist")
                .build();

    }

    @RequestMapping(value = "/register/doctor-staff", method = RequestMethod.POST)
    public AuthenticationResponse createDoctorStaff(@RequestBody final RegistrationForm registrationForm) {

        System.out.println("A POST request was made on /register/doctor-staff");

        Optional<User> userOptional = userService.getUser(registrationForm.getUsername());

        if(userOptional.isPresent()) {
            return AuthenticationResponse.builder()
                    .status("failure")
                    .userDto(null)
                    .message("There already exists a user with this username.")
                    .role("")
                    .build();
        }

        Optional<Role> role = roleService.getRoleByDescription(registrationForm.getUserType());

        Optional<Center> center;
        User user;
        center = this.centerService.findCenter(registrationForm.getCenterId());
        if(center.isPresent())
            user = userService.createUser(registrationForm.getUsername(), registrationForm.getPassword(), false, role.orElse(null), center.get());
        else
            user = userService.createUser(registrationForm.getUsername(), registrationForm.getPassword(), false, role.orElse(null), null);

        UserDto userDto1 = userConverter.convertModelToDto(user);
        return AuthenticationResponse.builder()
                .status("success")
                .role("Donor")
                .userDto(userDto1)
                .message("The user was successfully created")
                .build();

    }
}
