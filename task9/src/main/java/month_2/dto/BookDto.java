package month_2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {

    private Long id;

    private String name;

    private AuthorDto authorDto;

    private GenreDto genreDto;
}
