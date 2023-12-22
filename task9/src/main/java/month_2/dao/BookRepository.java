package month_2.dao;

import month_2.domain.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book-entity-graph")
    List<Book> findAll();

    @EntityGraph(value = "book-entity-graph")
    Optional<Book> findById(Long id);
}
