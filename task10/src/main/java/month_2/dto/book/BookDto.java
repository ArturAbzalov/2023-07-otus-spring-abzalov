package month_2.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import month_2.dto.AuthorDto;
import month_2.dto.GenreDto;

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
