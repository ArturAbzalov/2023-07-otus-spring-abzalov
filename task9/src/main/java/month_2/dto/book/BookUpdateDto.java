package month_2.dto.book;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookUpdateDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Поле name не должно быть пустым")
    private String name;

    @NotNull
    private Long authorId;

    @NotNull
    private Long genreId;
}
