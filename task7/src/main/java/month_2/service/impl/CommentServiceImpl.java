package month_2.service.impl;

import month_2.dao.BookDao;
import month_2.dao.CommentDao;
import month_2.domain.Book;
import month_2.domain.Comment;
import month_2.dto.CommentDto;
import month_2.exception.InconsistencyIdException;
import month_2.exception.NotFoundException;
import month_2.mapper.CommentMapper;
import month_2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    private final CommentMapper commentMapper;

    private final BookDao bookDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, CommentMapper commentMapper, BookDao bookDao) {
        this.commentDao = commentDao;
        this.commentMapper = commentMapper;
        this.bookDao = bookDao;
    }


    @Override
    @Transactional
    public List<CommentDto> getListByBookId(Long bookId) {
        return commentMapper.toListDto(commentDao.getAllByBookId(bookId));
    }

    @Override
    @Transactional
    public CommentDto getById(Long commentId) {
        Comment comment = commentDao.findById(commentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Author with id: %d not found", commentId)));
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public CommentDto create(CommentDto commentDto) {
        Book book = bookDao.findById(commentDto.getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Author with id: %d not found", commentDto.getId())));
        Comment comment = commentMapper.toEntity(commentDto, book);
        return commentMapper.toDto(commentDao.save(comment));
    }

    @Override
    @Transactional
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentDao.findById(commentDto.getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Comment with id: %d not found", commentDto.getId())));
        if (!comment.getBook().getId().equals(commentDto.getId())) {
            throw new InconsistencyIdException
                    (String.format("The received id does not match from DB: expected %d, actual %d",
                            comment.getBook().getId(),commentDto.getId()));
        }
        comment.setMessage(commentDto.getMessage());
        return commentMapper.toDto(commentDao.save(comment));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        commentDao.deleteById(id);
    }
}
