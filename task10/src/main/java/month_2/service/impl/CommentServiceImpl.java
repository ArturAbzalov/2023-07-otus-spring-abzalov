package month_2.service.impl;

import month_2.dao.BookRepository;
import month_2.dao.CommentRepository;
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

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final BookRepository bookRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper,
                              BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.bookRepository = bookRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getListByBookId(Long bookId) {
        return commentMapper.toListDto(commentRepository.getAllByBookId(bookId));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Author with id: %d not found", commentId)));
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public CommentDto create(CommentDto commentDto) {
        Book book = bookRepository.findById(commentDto.getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Author with id: %d not found", commentDto.getId())));
        Comment comment = commentMapper.toEntity(commentDto, book);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Comment with id: %d not found", commentDto.getId())));
        comment.setMessage(commentDto.getMessage());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
