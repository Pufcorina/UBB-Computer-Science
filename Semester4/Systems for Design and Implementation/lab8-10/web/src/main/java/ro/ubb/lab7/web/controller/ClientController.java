package ro.ubb.lab7.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab7.core.domain.Client;
import ro.ubb.lab7.core.service.ClientService;
import ro.ubb.lab7.web.converter.ClientConverter;
import ro.ubb.lab7.web.dto.ClientDto;
import ro.ubb.lab7.web.dto.ClientsDto;
import ro.ubb.lab7.web.dto.EmptyJsonResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    private static final Logger log =  LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<ClientDto> getAllClients() {
        log.trace("getAllClients() -- method entered");
        List<Client> clients = clientService.getAllClients();
        log.trace("getAllClients() -- method exit clients {}", clients);
        return new ArrayList<>(new ClientsDto(clientConverter.convertModelsToDtos(clients))
                .getClients());
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
    public ClientDto getClient(
            @PathVariable final Long clientId
    ) {
        log.trace("getClient() --- method entered clientId={}", clientId);
        Client client = clientService.findClient(clientId).orElse(null);
        ClientDto result = clientConverter.convertModelToDto(client);
        log.trace("getClient() --- method exit result={}", result);
        return result;
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    public ClientDto updateClient(
            @PathVariable final Long clientId,
            @RequestBody final ClientDto clientDto) {
        log.trace("updateClient --- method entered clientId {}, clientDto{}", clientId, clientDto);
        Client c = Client.builder().email(clientDto.getEmail())
                .name(clientDto.getName())
                .phoneNumber(clientDto.getPhoneNumber())
                .build();
        c.setId(clientId);
        Client client =  clientService.updateClient(
                c
        );
        ClientDto result = clientConverter.convertModelToDto(client);
        log.trace("updateClient --- method exit client {}", result);

        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ClientDto saveClient(
            @RequestBody final ClientDto clientDto) {
        log.trace("saveClient --- method entered clientDto {}", clientDto);
        Client client = Client.builder().email(clientDto.getEmail())
                .name(clientDto.getName())
                .phoneNumber(clientDto.getPhoneNumber())
                .build();
        Client c = clientService.addClient(client);
        ClientDto result = clientConverter.convertModelToDto(c);
        log.trace("saveClient --- method exit result {}", result);
        return result;
    }

    @RequestMapping(value = "clients/{clientId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClient(
            @PathVariable final Long clientId){
        log.trace("deleteClient --- method entered clientId {}", clientId);
        clientService.deleteClient(clientId);
        log.trace("deleteClient --- method exit");
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
