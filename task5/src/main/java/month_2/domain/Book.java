package month_2.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@Setter
public class Book {
    private Long id;

    private String name;

    private Long authorId;

    private Long genreId;
}
