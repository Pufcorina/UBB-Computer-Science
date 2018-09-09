package ro.ubb.donation.core.service;

import ro.ubb.donation.core.model.Center;

import java.util.List;
import java.util.Optional;

public interface CenterService {

    List<Center> findAll();

    Optional<Center> findCenter(int centerId);

    Center createCenter(String name, String address, String phone, String city, String email);

    Center updateCenter(int centerId, String name, String address, String phone, String city, String email);

    void deleteCenter(int centerId);
}
