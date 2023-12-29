package month_2.controller;


import lombok.RequiredArgsConstructor;
import month_2.dto.BookDto;
import month_2.service.BookService;
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

    @GetMapping("/books")
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "list";
    }

    @GetMapping(value = "/books", params = "id")
    public String getBook(@RequestParam long id, Model model) {
        BookDto bookDto = bookService.getById(id);
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("authorDto", bookDto.getAuthorDto());
        model.addAttribute("genreDto", bookDto.getGenreDto());
        return "edit";
    }

    @PostMapping("/books")
    public String create(BookDto bookDto) {
        bookService.create(bookDto);
        return "redirect:/books";
    }

    @PutMapping("/books")
    public String update(BookDto bookDto) {
        bookService.update(bookDto);
        return "redirect:/books";
    }

    @DeleteMapping("/books")
    public String delete(@RequestParam long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }


}
