package month_2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;

    private String firstName;

    private String lastName;
}
