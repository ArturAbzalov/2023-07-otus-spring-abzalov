package month_2.mapper;

import month_2.domain.Genre;
import month_2.dto.GenreDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreMapper {

    public Genre toEntity(GenreDto genreDto) {
        return Genre.builder().
                name(genreDto.getGenreName())
                .id(genreDto.getGenreId())
                .build();
    }

    public GenreDto toDto(Genre genre) {
        return GenreDto.builder().
                genreId(genre.getId())
                .genreName(genre.getName())
                .build();
    }

    public List<GenreDto> toListDto(List<Genre> genres) {
        return genres.stream().map(this::toDto).toList();
    }

}
