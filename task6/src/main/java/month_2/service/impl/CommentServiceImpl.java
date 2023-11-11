package month_2.service.impl;

import month_2.dao.BookDao;
import month_2.dao.CommentDao;
import month_2.domain.Book;
import month_2.domain.Comment;
import month_2.dto.CommentDto;
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
        return commentMapper.toListDto(commentDao.getListByBookId(bookId));
    }

    @Override
    @Transactional
    public CommentDto getById(Long commentId) {
        Comment comment = commentDao.getById(commentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Author with id: %d not found", commentId)));
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public CommentDto create(CommentDto commentDto) {
        Book book = bookDao.getById(commentDto.getBookDto().getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Author with id: %d not found", commentDto.getBookDto().getId())));
        Comment comment = commentMapper.toEntity(commentDto, book);
        return commentMapper.toDto(commentDao.create(comment));
    }

    @Override
    @Transactional
    public CommentDto update(CommentDto commentDto) {
        Book book = bookDao.getById(commentDto.getBookDto().getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Author with id: %d not found", commentDto.getBookDto().getId())));
        Comment comment = commentDao.update(commentMapper.toEntity(commentDto, book));
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        commentDao.deleteById(id);
    }
}
