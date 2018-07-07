package ro.ubb.lab7.web.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(callSuper = true)
public class MovieRentalDto{
    private Long movieId;
    private Long clientId;
    @Min(0)
    @Max(10)
    private Integer rating;
    private String movieTitle;
}
