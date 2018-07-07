package ro.ubb.lab7.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.lab7.core.domain.Client;
import ro.ubb.lab7.core.domain.Movie;
import ro.ubb.lab7.web.dto.ClientDto;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto>{
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        throw new RuntimeException("Not yet implemented!");
        /*
        Client c = new Client(
                dto.getName(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                new HashSet<>()
        );
        c.setId(dto.getId());
        return c;
        */
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        if(client.getRentals() == null)
            client.setRentals(new HashSet<>());
        ClientDto c = new ClientDto(
                client.getName(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getMovies().stream().map(Movie::getId).collect(Collectors.toSet())
        );
        c.setId(client.getId());
        return c;
    }
}
