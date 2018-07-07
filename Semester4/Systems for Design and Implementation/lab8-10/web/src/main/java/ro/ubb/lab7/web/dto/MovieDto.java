package ro.ubb.lab7.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDto extends BaseDto {
    private String title;
    private String director;
    private String genre;
    private Integer year;
    private Integer duration;
    private Set<Long> clients;

    @Override
    public String toString() {
        return "MovieDto{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                '}' + super.toString();
    }
}
