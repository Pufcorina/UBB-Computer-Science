//package ro.ubb.web.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.ubb.core.domain.Client;
//import ro.ubb.core.service.ClientService;
//import ro.ubb.web.converter.ClientConverter;
//import ro.ubb.web.dto.ClientDto;
//import ro.ubb.web.dto.ClientsDto;
//import ro.ubb.web.dto.EmptyJsonResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class ClientController {
//
//    private static final Logger log =  LoggerFactory.getLogger(ClientController.class);
//
//    @Autowired
//    private ClientService clientService;
//
//    @Autowired
//    private ClientConverter clientConverter;
//
//    @RequestMapping(value = "/clients", method = RequestMethod.GET)
//    public List<ClientDto> getAllClients() {
//        log.trace("getAllClients() -- method entered");
//        List<Client> clients = clientService.getAllClients();
//        log.trace("getAllClients() -- method exit clients {}", clients);
//        return new ArrayList<>(new ClientsDto(clientConverter.convertModelsToDtos(clients))
//                .getClients());
//    }
//
//    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
//    public ClientDto getClient(
//            @PathVariable final Long clientId
//    ) {
//        log.trace("getClient() --- method entered clientId={}", clientId);
//        Client client = clientService.findClient(clientId).orElse(null);
//        ClientDto result = clientConverter.convertModelToDto(client);
//        log.trace("getClient() --- method exit result={}", result);
//        return result;
//    }
//
//    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
//    public ClientDto updateClient(
//            @PathVariable final Long clientId,
//            @RequestBody final ClientDto clientDto) {
//        log.trace("updateClient --- method entered clientId {}, clientDto{}", clientId, clientDto);
//        Client c = Client.builder().email(clientDto.getEmail())
//                .name(clientDto.getName())
//                .phoneNumber(clientDto.getPhoneNumber())
//                .build();
//        c.setId(clientId);
//        Client client =  clientService.updateClient(
//                c
//        );
//        ClientDto result = clientConverter.convertModelToDto(client);
//        log.trace("updateClient --- method exit client {}", result);
//
//        return result;
//    }
//
//    @RequestMapping(value = "/clients", method = RequestMethod.POST)
//    public ClientDto saveClient(
//            @RequestBody final ClientDto clientDto) {
//        log.trace("saveClient --- method entered clientDto {}", clientDto);
//        Client client = Client.builder().email(clientDto.getEmail())
//                .name(clientDto.getName())
//                .phoneNumber(clientDto.getPhoneNumber())
//                .build();
//        Client c = clientService.addClient(client);
//        ClientDto result = clientConverter.convertModelToDto(c);
//        log.trace("saveClient --- method exit result {}", result);
//        return result;
//    }
//
//    @RequestMapping(value = "clients/{clientId}", method = RequestMethod.DELETE)
//    public ResponseEntity deleteClient(
//            @PathVariable final Long clientId){
//        log.trace("deleteClient --- method entered clientId {}", clientId);
//        clientService.deleteClient(clientId);
//        log.trace("deleteClient --- method exit");
//        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
//    }
//}
//


//import org.springframework.web.bind.annotation.*;
//
//@GetMapping("/years")
//public Set<Integer> getYears() {
//        log.info("Retrieveing years from service");
//        List<Author> authors = authorService.getAll();
//        List<Book> books = new ArrayList<>();
//        for(Author author: authors){
//        for(Book book: author.getBooks()){
//        books.add(book);
//        }
//        }
//        return books.stream()
//        .map(book -> book.getYear())
//        .collect(Collectors.toSet());
//        }
//
//@GetMapping("/{id}")
//public AuthorDto get(@PathVariable(value = "id") Long id) {
//        log.info("Retrieving author with id: " + id +" from service");
//        return authorConverter.convertModelToDto(authorService.get(id));
//        }
//
//@PostMapping()
//public AuthorDto addOrUpdate(@RequestBody AuthorDto authorDto) {
//        Author author = new Author();
//        if(authorDto.getId() != null)
//        author.setId(authorDto.getId());
//        author.setName(authorDto.getName());
//        author.setSsn(authorDto.getSsn());
//        author.setPublisher(authorDto.getPublisher());
//        log.info("Trying to update or add author " + author);
//        return authorConverter.convertModelToDto(authorService.addOrUpdate(author));
//        }
//
//@GetMapping("/filtered")
//public Set<AuthorDto> getFiltered(@RequestParam Long authorId, @RequestParam Integer year) {
//        log.info("Retrieving filtered authors from service, filtering by id=" + authorId + " and year="+year);
//        List<Book> books = authorService.findBooksByAuthorAndYear(authorId, year);
//        List<Author> authors = new ArrayList<>();
//        if(authorId != -1) {
//        Author author = authorService.get(authorId);
//        authors.add(author);
//        author.setBooks(books);
//        }else if(year != -1){
//        List<Book> authorBooks;
//        List<Author> authorsFiltered = new ArrayList<>();
//        for (Author author:authorService.getAll()){
//        authorBooks = new ArrayList<>();
//        for (Book b: author.getBooks())
//        if(b.getYear().equals(year))
//        authorBooks.add(b);
//        author.setBooks(authorBooks);
//        authorsFiltered.add(author);
//        }
//        authors = authorsFiltered;
//        }
//        return authorConverter.convertModelsToDtos(authors);
//        }
//
//@DeleteMapping("/{id}")
//public void delete(@PathVariable(value = "id") Long id) {
//        log.info("Trying to delete author with id: " + id);
//        authorService.delete(id);
//        }
//
//@DeleteMapping()
//public void delete(@RequestBody Author author) {
//        authorService.delete(author);
//        }