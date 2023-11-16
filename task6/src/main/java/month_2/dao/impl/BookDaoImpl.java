package month_2.dao.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import month_2.dao.BookDao;
import month_2.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@Slf4j
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public void deleteById(Book book) {
        entityManager.remove(book);
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put(FETCH.getKey(), entityGraph);
        return Optional.ofNullable(entityManager.find(Book.class, id, properties));
    }

}
