package ro.ubb.web.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthorDto {
    private Long id;
    private String ssn;
    private String name;
    private String publisher;
    private List<BookDto> books;
}
