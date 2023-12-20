package month_2.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AuthorDto {

    private String authorId;

    private String authorFirstName;

    private String authorLastName;
}
