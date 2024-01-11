package month_2.dao;

import month_2.controller.BookController;
import month_2.dto.AuthorDto;
import month_2.dto.GenreDto;
import month_2.dto.book.BookDto;
import month_2.dto.book.BookUpdateDto;
import month_2.service.AuthorService;
import month_2.service.BookService;
import month_2.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private AuthorService authorService;

    @Test
    void getAll() throws Exception {
        AuthorDto firstAuthor = AuthorDto.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        AuthorDto secondAuthor = AuthorDto.builder().id(2L).firstName("Alex").lastName("Pushkin").build();
        GenreDto genre = GenreDto.builder().id(1L).name("roman").build();
        BookDto firstBook = BookDto.builder().id(1L).authorDto(firstAuthor).genreDto(genre).name("War&peace").build();
        BookDto secondBook = BookDto.builder().id(2L).authorDto(secondAuthor).genreDto(genre).name("The Tale of Tsar Saltan").build();
        List<BookDto> bookDtoList = List.of(firstBook, secondBook);
        when(bookService.getAll()).thenReturn(bookDtoList);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("War&amp;peace")))
                .andExpect(content().string(containsString("The Tale of Tsar Saltan")))
                .andExpect(content().string(containsString("Lev")))
                .andExpect(content().string(containsString("Tolstoy")))
                .andExpect(content().string(containsString("Pushkin")))
                .andExpect(content().string(containsString("Alex")))
                .andExpect(content().string(containsString("roman")));
    }

    @Test
    void getOne() throws Exception {
        Long id = 1L;
        AuthorDto firstAuthor = AuthorDto.builder().id(1L).firstName("Lev").lastName("Tolstoy").build();
        AuthorDto secondAuthor = AuthorDto.builder().id(2L).firstName("Alex").lastName("Pushkin").build();
        GenreDto firstGenre = GenreDto.builder().id(1L).name("roman").build();
        GenreDto secondGenre = GenreDto.builder().id(1L).name("fantasy").build();
        List<GenreDto> genreDtoList = List.of(firstGenre, secondGenre);
        List<AuthorDto> authorDtoList = List.of(firstAuthor, secondAuthor);
        BookUpdateDto firstBook = BookUpdateDto.builder().id(1L).authorId(1L).genreId(1L).name("War&peace").build();
        when(genreService.getAll()).thenReturn(genreDtoList);
        when(authorService.getAll()).thenReturn(authorDtoList);
        when(bookService.getById(id)).thenReturn(firstBook);
        mockMvc.perform(get("/books").param("id","1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("War&amp;peace")))
                .andExpect(content().string(containsString("Tolstoy")))
                .andExpect(content().string(containsString("Lev")))
                .andExpect(content().string(containsString("roman")))
                .andExpect(content().string(containsString("fantasy")))
                .andExpect(content().string(containsString("Alex")))
                .andExpect(content().string(containsString("Pushkin")));

    }

    @Test
    void deleteOne() throws Exception {
        mockMvc.perform(delete("/books").param("id","1")).andExpect(status().is3xxRedirection());
        verify(bookService).deleteById(1);
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/books")
                        .param("name","Test")
                        .param("authorId","1")
                        .param("genreId","1"))
                .andExpect(status().is3xxRedirection());
        verify(bookService).create(BookDto.builder().name("Test").genreDto(GenreDto.builder().id(1L).build()).authorDto(AuthorDto.builder().id(1L).build()).build());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/books")
                .param("id","1")
                .param("name","War")
                .param("authorId","1")
                .param("genreId","1"))
                .andExpect(status().is3xxRedirection());
        verify(bookService).update(BookUpdateDto.builder()
                .id(1L).name("War")
                .genreId(1L)
                .authorId(1L)
                .build());
    }
}