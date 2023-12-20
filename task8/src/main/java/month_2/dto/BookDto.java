package month_2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BookDto {

    private String id;

    private String name;

    private AuthorDto authorDto;

    private GenreDto genreDto;
}
