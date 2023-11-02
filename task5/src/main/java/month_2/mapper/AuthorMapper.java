package month_2.mapper;

import month_2.domain.Author;
import month_2.dto.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorDto authorDto) {
        return Author.builder()
                .id(authorDto.getAuthorId())
                .firstName(authorDto.getAuthorFirstName())
                .lastName(authorDto.getAuthorLastName())
                .build();
    }

    public AuthorDto toDto(Author author) {
        return AuthorDto.builder()
                .authorFirstName(author.getFirstName())
                .authorLastName(author.getLastName())
                .authorId(author.getId())
                .build();
    }

}
