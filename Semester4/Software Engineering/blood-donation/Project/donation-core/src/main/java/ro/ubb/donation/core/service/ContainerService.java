package ro.ubb.donation.core.service;

import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.model.Container;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ContainerService {
    Optional<Container> findContainer(int containerId);

    List<Container> findAll();

    Container updateContainer(int id, Date expirationDate, Center centerId, String bloodGroup, String rh, String componentType);

    Container createContainer(Date expirationDate, Center centerId, String bloodGroup, String rh, String componentType);

    void deleteContainer(int containerId);

    List<Container> getContainersByCenterId(int centerId);

    long getNrContainersUsable(String componentType, String bloodType, String rh, int centerId);
    long getNrContainersUsable(String componentType, int centerId);

    long getNrContainersExpired(String componentType, String bloodType, String rh, int centerId);
    long getNrContainersExpired(String componentType, int centerId);
}
