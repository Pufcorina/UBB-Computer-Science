package ro.ubb.lab7.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.lab7.core.domain.Client;
import ro.ubb.lab7.core.domain.MovieRental;
import ro.ubb.lab7.core.repository.ClientJpaRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);
    @Autowired
    private ClientJpaRepository clients;

    public Optional<Client> findClient(Long clientId){
        LOG.trace("findClient --- method entered - clientId {}", clientId);
        Optional<Client> client = clients.findByIdWithRentalsWithAndMovies(clientId);
        LOG.trace("findClient --- method exit - client {}", client);
        return client;
    }

    public Client addClient(@Valid Client client) {
        String name = client.getName();
        String phoneNumber = client.getPhoneNumber();
        String email = client.getEmail();
        LOG.trace("addClient --- method entered - name {}, phoneNumber {}, email {}", name, phoneNumber, email);
        Client c = Client.builder().name(name).phoneNumber(phoneNumber).email(email).build();
        c = clients.save(c);
        LOG.trace("addClient --- method exit client {}", c);
        return c;
    }

    @Transactional
    public Client updateClient(@Valid Client client) {
        Long clientId = client.getId();
        String name = client.getName();
        String phoneNumber = client.getPhoneNumber();
        String email = client.getEmail();
        LOG.trace("updateClient --- method entered - clientId {}, email {}, name {}, phoneNumber {}", clientId, email, name, phoneNumber);
        Optional<Client> cli = clients.findById(clientId);
        cli.ifPresent(c ->{
            c.setEmail(email);
            c.setName(name);
            c.setPhoneNumber(phoneNumber);
        });
        LOG.trace("updateClient --- method exit client {}", cli);
        return cli.orElse(null);
    }

    public void deleteClient(Long id){
        LOG.trace("deleteClient --- method entered - id {}", id);
        clients.deleteById(id);
        LOG.trace("deleteClient --- method exit");
    }

    public List<Client> getAllClients(){
        LOG.trace("getAllClients --- method entered");
        List<Client> m = clients.findAllWithRentalsAndMovies();
        LOG.trace("getAllClients --- method exit result {}", m);
        return m;
    }
}
