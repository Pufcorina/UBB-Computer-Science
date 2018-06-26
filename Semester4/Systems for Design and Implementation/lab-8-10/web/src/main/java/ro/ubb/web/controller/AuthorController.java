package ro.ubb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.core.domain.Author;
import ro.ubb.core.domain.Book;
import ro.ubb.core.service.AuthorService;
import ro.ubb.web.converter.AuthorConverter;
import ro.ubb.web.dto.AuthorDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/persons")
@RestController
public class AuthorController {
    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    public AuthorController(AuthorService authorService, AuthorConverter authorConverter) {
        this.authorService = authorService;
        this.authorConverter = authorConverter;
    }

    private final AuthorConverter authorConverter;
    private final AuthorService authorService;

    @GetMapping()
    public Set<AuthorDto> getAll() {
        log.info("Retrieving all authors from service");
        return authorConverter.convertModelsToDtos(authorService.getAll());
    }
}
