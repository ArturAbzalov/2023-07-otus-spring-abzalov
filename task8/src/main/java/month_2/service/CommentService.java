package month_2.service;

import month_2.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getListByBookId(String bookId);

    CommentDto getById(String commentId);

    CommentDto create(CommentDto commentDto);

    CommentDto update(CommentDto commentDto);

    void deleteById(String id);

}
