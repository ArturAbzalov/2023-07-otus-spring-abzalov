package month_1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Student {
    private String firstName;

    private String lastName;

    private int balls;

    public String toString() {
        return firstName + " " + lastName;
    }
}
