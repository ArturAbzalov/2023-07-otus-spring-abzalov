package month_2.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Getter используется в мапперах, Equals&hashcode в тестах, без них некоррентно сравнивает объекты
@Getter
@Builder
@Setter
@EqualsAndHashCode
public class Book {
    private Long id;

    private String name;

    private Author author;

    private Genre genre;
}
