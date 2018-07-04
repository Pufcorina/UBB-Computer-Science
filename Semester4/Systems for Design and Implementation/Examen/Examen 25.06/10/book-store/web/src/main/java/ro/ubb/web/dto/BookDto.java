package ro.ubb.web.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {
    private Long authorId;
    private String title;
    private Integer year;
}
