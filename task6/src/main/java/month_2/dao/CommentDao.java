package month_2.dao;

import month_2.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    List<Comment> getListByBookId(Long bookId);

    Optional<Comment> getById(Long commentId);

    Comment create(Comment comment);

    Comment update(Comment comment);

    void deleteById(Long id);
}
