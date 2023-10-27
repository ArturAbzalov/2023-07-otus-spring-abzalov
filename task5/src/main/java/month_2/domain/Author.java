package month_2.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

//Getter используется в мапперах, Equals&hashcode в тестах, без них некоррентно сравнивает объекты
@Getter
@Builder
@EqualsAndHashCode
public class Author {
    private Long id;

    private String firstName;

    private String lastName;
}
