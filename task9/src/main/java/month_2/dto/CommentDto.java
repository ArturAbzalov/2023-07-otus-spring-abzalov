package month_2.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CommentDto {

    private Long id;

    private Long bookId;

    private String message;

}
