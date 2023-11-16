package month_2.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import month_2.dao.AuthorDao;
import month_2.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public AuthorDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Optional<Author> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }
}
