package month_1.dao;

import month_1.model.Student;
import month_1.service.IOService;
import org.springframework.stereotype.Component;

@Component
public class StudentDaoImpl implements StudentDao {

    private final IOService ioService;

    public StudentDaoImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student create() {
        ioService.print("Введите свое имя:\n");
        String firstName = ioService.nextLine();
        ioService.print("Введите свою фамилию:\n");
        String lastName = ioService.nextLine();
        return new Student(lastName, firstName, 0);
    }

}
