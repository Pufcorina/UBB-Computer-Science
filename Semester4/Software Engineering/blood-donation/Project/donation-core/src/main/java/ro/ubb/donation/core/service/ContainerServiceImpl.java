package ro.ubb.donation.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.model.Container;
import ro.ubb.donation.core.repository.ContainerRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContainerServiceImpl implements ContainerService {
    private static final Logger log = LoggerFactory.getLogger(ContainerServiceImpl.class);

    @Autowired
    private ContainerRepository containerRepository;

    @Override
    public Optional<Container> findContainer(int containerId) {
        log.trace("findContainer:containerId={}", containerId);
        Optional<Container> containerOptional= containerRepository.findById(containerId);
        log.trace("findContainer: containerOptional={}", containerOptional);
        return containerOptional;
    }

    @Override
    public List<Container> findAll() {
        log.trace("findAll --- method entered");

        List<Container> containers = containerRepository.findAll();

        log.trace("findAll: containers={}", containers);

        return containers;
    }

    @Override
    @Transactional
    public Container updateContainer(int containerId, Date expirationDate, Center centerId, String bloodGroup, String rh, String componentType) {
        log.trace("updateContainer: expirationDate={}, centerId={}, bloodGroup={}, rh={}, componentType={}", expirationDate, centerId, bloodGroup, rh, componentType);

        Optional<Container> container = containerRepository.findById(containerId);

        container.ifPresent(c -> {
            c.setBloodGroup( bloodGroup );
            c.setCenterId( centerId );
            c.setComponentType( componentType );
            c.setExpirationDate( expirationDate );
        });

        log.trace("updateContainer: container={}", container.get());

        return container.orElse(null);

    }

    @Override
    @Transactional
    public Container createContainer(Date expirationDate, Center centerId, String bloodGroup, String rh, String componentType) {
        log.trace("createContainer: expirationDate={}, centerId={}, bloodGroup={}, rh={}, componentType={}", expirationDate, centerId, bloodGroup, rh, componentType);

        Container container = Container.builder()
                .expirationDate( expirationDate )
                .centerId( centerId )
                .bloodGroup( bloodGroup )
                .rh( rh )
                .componentType( componentType )
                .build();
        container = containerRepository.save(container);
        log.trace("createContainer: container={}", container);

        return container;
    }

    @Override
    @Transactional
    public void deleteContainer(int containerId) {
        log.trace("deleteContainer: containerId={}", containerId);

        containerRepository.deleteById(containerId);

        log.trace("deleteContainer - method end");
    }

    public List<Container> getContainersByCenterId(int centerId) {
        return this.findAll().stream().filter( c -> c.getCenterId().getId() == centerId ).collect(Collectors.toList());
    }

    @Override
    public long getNrContainersUsable(String componentType, String bloodType, String rh, int centerId) {
        List<Container> containers = this.containerRepository.findAll().stream().filter(c->(c.getCenterId().getId()==centerId && c.getComponentType().equals(componentType)))
                .filter(c->(c.getBloodGroup().equals(bloodType) && c.getRh().equals(rh))).collect(Collectors.toList());
        long result = containers.stream().filter(c->c.getExpirationDate().after(new Date())).count();
        return result;
    }

    @Override
    public long getNrContainersUsable(String componentType, int centerId) {
        List<Container> containers = this.containerRepository.findAll().stream().filter(c->(c.getCenterId().getId()==centerId && c.getComponentType().equals(componentType)))
               .collect(Collectors.toList());
        long result = containers.stream().filter(c->c.getExpirationDate().after(new Date())).count();
        return result;
    }

    @Override
    public long getNrContainersExpired(String componentType, String bloodType, String rh, int centerId) {
        List<Container> containers = this.containerRepository.findAll().stream().filter(c->(c.getCenterId().getId()==centerId && c.getComponentType().equals(componentType)))
                .filter(c->(c.getBloodGroup().equals(bloodType) && c.getRh().equals(rh))).collect(Collectors.toList());
        long result = containers.stream().filter(c->c.getExpirationDate().before(new Date())).count();
        return result;
    }

    @Override
    public long getNrContainersExpired(String componentType, int centerId) {
        List<Container> containers = this.containerRepository.findAll().stream().filter(c->(c.getCenterId().getId()==centerId && c.getComponentType().equals(componentType)))
                .collect(Collectors.toList());
        long result = containers.stream().filter(c->c.getExpirationDate().before(new Date())).count();
        return result;
    }
}
