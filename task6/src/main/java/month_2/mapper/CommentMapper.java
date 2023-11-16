package month_2.mapper;

import month_2.domain.Book;
import month_2.domain.Comment;
import month_2.dto.CommentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentMapper {

    public Comment toEntity(CommentDto commentDto, Book book) {
        return Comment.builder()
                .id(commentDto.getId())
                .message(commentDto.getMessage())
                .book(book)
                .build();
    }

    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .message(comment.getMessage())
                .build();
    }

    public List<CommentDto> toListDto(List<Comment> comments) {
        return comments.stream().map(this::toDto).toList();
    }
}
