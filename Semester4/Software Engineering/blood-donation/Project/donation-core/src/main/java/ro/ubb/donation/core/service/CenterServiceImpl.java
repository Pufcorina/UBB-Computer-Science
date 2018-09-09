package ro.ubb.donation.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.repository.CenterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {
    private static final Logger log = LoggerFactory.getLogger(ContainerServiceImpl.class);

    @Autowired
    private CenterRepository centerRepository;

    @Override
    public List<Center> findAll() {
        return this.centerRepository.findAll();
    }

    @Override
    public Optional<Center> findCenter(int centerId) {
        return this.centerRepository.findById(centerId);
    }

    @Override
    @Transactional
    public Center createCenter(String name, String address, String phone, String city, String email) {
        log.trace("createCenter: name={}, address={}, phone={}, city={}, email={}", name, address, phone, city, email);

        Center center = Center.builder()
                .name( name )
                .address( address )
                .phone( phone )
                .city( city )
                .email( email )
                .build();
        center = centerRepository.save(center);
        log.trace("createCenter: center={}", center);

        return center;
    }

    @Override
    @Transactional
    public Center updateCenter(int centerId, String name, String address, String phone, String city, String email) {
        log.trace("updateCenter: name={}, address={}, phone={}, city={}, email={}", name, address, phone, city, email);

        Optional<Center> center = centerRepository.findById(centerId);

        center.ifPresent(c -> {
            c.setName( name );
            c.setAddress( address );
            c.setPhone( phone );
            c.setCity( city );
            c.setEmail( email );
        });

        log.trace("updateCenter: center={}", center.get());

        return center.orElse(null);
    }

    @Override
    @Transactional
    public void deleteCenter(int centerId) {
        log.trace("deleteCenter: centerId={}", centerId);

        centerRepository.deleteById(centerId);

        log.trace("deleteCenter - method end");
    }
}
