package month_2.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private long id;

    private String name;

    private Author author;

    private Genre genre;
}
