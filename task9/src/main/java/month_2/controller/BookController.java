package month_2.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import month_2.dto.book.BookCreateDto;
import month_2.dto.book.BookUpdateDto;
import month_2.service.AuthorService;
import month_2.service.BookService;
import month_2.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final GenreService genreService;

    private final AuthorService authorService;

    @GetMapping("/books")
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "list";
    }

    @GetMapping(value = "/books", params = "id")
    public String getBook(@RequestParam long id, Model model) {
        BookUpdateDto bookUpdateDto = bookService.getById(id);
        model.addAttribute("bookDto", bookUpdateDto);
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("authors", authorService.getAll());
        return "edit";
    }

    @GetMapping("/books/create")
    public String getFormCreate(Model model) {
        model.addAttribute("bookDto",new BookCreateDto());
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("authors", authorService.getAll());
        return "create";
    }

    @PostMapping("/books")
    public String create(@Valid BookCreateDto bookCreateDto) {
        bookService.create(bookCreateDto);
        return "redirect:/books";
    }

    @PutMapping("/books")
    public String update(@Valid BookUpdateDto bookUpdateDto) {
        bookService.update(bookUpdateDto);
        return "redirect:/books";
    }

    @DeleteMapping("/books")
    public String delete(@RequestParam long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
