package month_2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class BookDto {

    private Long id;

    private String name;

    private AuthorDto authorDto;

    private GenreDto genreDto;
}
