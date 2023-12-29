package month_2.mapper;

import month_2.domain.Author;
import month_2.dto.AuthorDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorDto authorDto) {
        return Author.builder()
                .id(authorDto.getId())
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .build();
    }

    public AuthorDto toDto(Author author) {
        return AuthorDto.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .id(author.getId())
                .build();
    }

    public List<AuthorDto> toListDto(List<Author> authors) {
        return authors.stream().map(this::toDto).toList();
    }

}
