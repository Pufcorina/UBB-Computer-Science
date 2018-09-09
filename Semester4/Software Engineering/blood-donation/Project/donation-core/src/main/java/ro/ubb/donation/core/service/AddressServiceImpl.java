package ro.ubb.donation.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.Address;
import ro.ubb.donation.core.model.Profile;
import ro.ubb.donation.core.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Optional<Address> findAddress(int addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public Address updateAddress(int addressId, String homeAddress, String currentHomeAddress, String city, String country, String currentCity, String currentCountry) {
        log.trace("updateAddress: addressId={}, homeAddress={}, currentHomeAddress={}, city={}, country={}, currentCity={}, currentCountry={}", addressId, homeAddress, currentHomeAddress, city, country, currentCity, currentCountry);

        Optional<Address> address = addressRepository.findById(addressId);
        if (address.isPresent()){
            Address a =address.get();
            a.setHomeAddress(homeAddress);
            a.setCurrentHomeAddress(currentHomeAddress);
            a.setCity(city);
            a.setCountry(country);
            a.setCurrentCity(currentCity);
            a.setCurrentCountry(currentCountry);
        }

        log.trace("updateAddress={}");
        return address.orElse(null);
    }

    @Override
    @Transactional
    public Address createAddress(String homeAddress, String currentHomeAddress, String city, String country, String currentCity, String currentCountry) {
        log.trace("updateAddress: homeAddress={}, currentHomeAddress={}, city={}, country={}, currentCity={}, currentCountry={}", homeAddress, currentHomeAddress, city, country, currentCity, currentCountry);

        Address address = Address.builder()
                .homeAddress(homeAddress)
                .currentHomeAddress(currentHomeAddress)
                .city(city)
                .country(country)
                .currentCity(currentCity)
                .currentCountry(currentCountry)
                .build();

        addressRepository.save(address);

        log.trace("updateAddress={}", address);
        return address;
    }

    @Override
    @Transactional
    public void deleteAddress(int addressId) {
        log.trace("deleteAddress: addressId={}", addressId);

        Optional<Address> address = addressRepository.findById(addressId);
        address.ifPresent(a -> addressRepository.delete(a));
    }
}
