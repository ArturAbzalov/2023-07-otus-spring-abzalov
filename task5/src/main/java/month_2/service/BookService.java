package month_2.service;

import month_2.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto create(BookDto bookDto);

    void deleteById(long id);

    List<BookDto> getAll();

    BookDto update(BookDto bookDto);

    BookDto getById(Long id);
}
