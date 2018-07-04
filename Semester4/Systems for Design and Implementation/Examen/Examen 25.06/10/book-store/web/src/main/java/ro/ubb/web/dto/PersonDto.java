package ro.ubb.web.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PersonDto {
    private Long id;
    private String name;
    private String publisher;
    private List<BookDto> books;
}
