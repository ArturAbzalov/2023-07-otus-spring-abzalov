package month_2.service;

import month_2.dto.book.BookDto;
import month_2.dto.book.BookUpdateDto;

import java.util.List;

public interface BookService {

    void deleteById(long id);

    List<BookDto> getAll();

    BookDto update(BookUpdateDto bookUpdateDto);

    BookDto create(BookDto bookDto);

    BookUpdateDto getById(Long id);
}
