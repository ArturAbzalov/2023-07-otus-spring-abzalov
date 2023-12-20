package month_2.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GenreDto {

    private String genreId;

    private String genreName;
}
