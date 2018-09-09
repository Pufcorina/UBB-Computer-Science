package ro.ubb.donation.core.service;

import ro.ubb.donation.core.model.Address;
import ro.ubb.donation.core.model.Profile;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<Address> findAddress(int AddressId);

    List<Address> findAll();

    Address updateAddress(int AddressId, String homeAddress, String currentHomeAddress, String city, String country, String currentCity, String currentCountry);

    Address createAddress(String homeAddress, String currentHomeAddress, String city, String country, String currentCity, String currentCountry);

    void deleteAddress(int AddressId);
}
