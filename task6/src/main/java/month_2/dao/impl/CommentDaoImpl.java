package month_2.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import month_2.dao.CommentDao;
import month_2.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public CommentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Comment> getListByBookId(Long bookId) {
//        EntityGraph<?> entityGraph = entityManager.getEntityGraph("comment-entity-graph");
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.book.id=:id",
                Comment.class);
        query.setParameter("id", bookId);
//        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<Comment> getById(Long commentId) {
        return Optional.ofNullable(entityManager.find(Comment.class, commentId));
    }

    @Override
    public Comment create(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Comment c where c.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
