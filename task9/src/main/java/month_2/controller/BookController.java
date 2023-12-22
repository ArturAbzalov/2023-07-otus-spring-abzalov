package month_2.controller;


import lombok.RequiredArgsConstructor;
import month_2.domain.Book;
import month_2.dto.BookDto;
import month_2.service.BookService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping(value = "/books", params = "id")
    public List<BookDto> getBook(@RequestParam long id) {
        return null;
    }

    @PostMapping
    public Book create() {
        return null;
    }

    @PatchMapping
    public Book update() {
        return null;
    }

    @DeleteMapping
    public Book delete() {
        return null;
    }


}
