package month_2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class BookDto {
    private Long id;

    private String name;

    private String authorFirstName;

    private String authorLastName;

    private String genreName;

    private Long genreId;

    private Long authorId;
}
