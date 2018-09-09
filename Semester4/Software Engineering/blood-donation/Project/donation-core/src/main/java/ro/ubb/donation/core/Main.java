package ro.ubb.donation.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.Address;
import ro.ubb.donation.core.model.Profile;
import ro.ubb.donation.core.model.Role;
import ro.ubb.donation.core.model.User;
import ro.ubb.donation.core.repository.RoleRepository;
import ro.ubb.donation.core.repository.UserRepository;
import ro.ubb.donation.core.service.AddressService;
import ro.ubb.donation.core.service.ProfileService;
import ro.ubb.donation.core.service.RoleService;
import ro.ubb.donation.core.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.donation.core");

        //service-uri
        RoleService roleService = context.getBean(RoleService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        UserService userService = context.getBean(UserService.class);
        AddressService addressService = context.getBean(AddressService.class);

        //cream RoleTable
        Role donor = roleService.createRole("Donor");
        Role doctor = roleService.createRole("Doctor");
        Role pers = roleService.createRole("Hospital Personnel");


        Address addr = addressService.createAddress("home1", "curhome", "city", "coun", "curc", "curc");
        Address addr2 = addressService.createAddress("home2", "curhome", "city", "coun", "curc", "curc");

        //Profile p = profileService.createProfile("profile1", "ln", "21/12/2012", "female", "type", "st", "+", "a", "29708062334", "yes", "yes", "yes");
        //Profile p2 = profileService.createProfile("prof2", "last", "02/02/2000", "F", "2", "298748736287", "+", "A", "8473285", "yes", "no", "no");

        Profile p2=profileService.createProfile("f", "l","02-02-2000", "f", "+", "984329", "+", "kdjsk", "98432", "no", "mo", "no");

//        User u2 = userService.createUser("user2", "pas2", true, doctor);
//        userService.updateUser(u2.getId(), u2.getUsername(), u2.getPassword(), u2.isLogged(), u2.getRole(), addr2, p2);

        //addressService.deleteAddress(2);
        //profileService.deleteProfile(1);
        //userService.deleteUser(1);

//        userService.createUser("mariaungur1","pass",true,roleService.getRoleByDescription("Donor").orElse(null));
//        userService.createUser("marialazar1","pass",false,roleService.getRoleByDescription("Donor").orElse(null));
//        userService.createUser("nicoletaungur1","pass",true,roleService.getRoleByDescription("Doctor").orElse(null));
//        userService.createUser("mariaungur2","pass",true,roleService.getRoleByDescription("Hospital Personnel").orElse(null));
//        userService.createUser("marialazar2","pass",false,roleService.getRoleByDescription("Hospital Personnel").orElse(null));
//        userService.createUser("nicoletaungur2","pass",true,roleService.getRoleByDescription("Hospital Personnel").orElse(null));
//


        System.out.println("###############################################3");
        List<Address> adrs = addressService.findAll();
        for (Address a : adrs) {
            System.out.println(a);
        }
        System.out.println("###############################################3");
        List<Profile> profiles = profileService.findAll();
        for (Profile profile : profiles) {
            System.out.println(profile);
        }
        System.out.println("#################################################33333333");
        //UserService userService = context.getBean(UserService.class);
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }


//        userService.createUser("mariaungur1","pass",true,roleService.getRoleByDescription("Donor").orElse(null));
//        userService.createUser("marialazar1","pass",false,roleService.getRoleByDescription("Donor").orElse(null));
//        userService.createUser("nicoletaungur1","pass",true,roleService.getRoleByDescription("Doctor").orElse(null));
//        userService.createUser("mariaungur2","pass",true,roleService.getRoleByDescription("Hospital Personnel").orElse(null));
//        userService.createUser("marialazar2","pass",false,roleService.getRoleByDescription("Hospital Personnel").orElse(null));
//        userService.createUser("nicoletaungur2","pass",true,roleService.getRoleByDescription("Hospital Personnel").orElse(null));
//        userService.deleteUser(1);
//        userService.deleteUser(4);
//        roleService.deleteRole(3);

//        List<Role> roles = roleService.findAll();
//        roles.forEach(System.out::println);
//
//        List<User> users = userService.findAll();
//        users.forEach(System.out::println);
//        System.out.print("Bye");


    }

    @Transactional
    public void seteaza(User u2, Address addr2, Profile p2) {
        u2.setAddress(addr2);
        u2.setProfile(p2);
    }


}
