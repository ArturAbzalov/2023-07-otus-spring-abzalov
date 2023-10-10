package month_1.dao;

import month_1.model.Student;
import month_1.service.IOService;
import month_1.service.LocalizationService;
import org.springframework.stereotype.Component;

@Component
public class StudentDaoImpl implements StudentDao {

    private final IOService ioService;

    private final LocalizationService localizationService;

    public StudentDaoImpl(IOService ioService, LocalizationService localizationService) {
        this.ioService = ioService;
        this.localizationService = localizationService;
    }

    @Override
    public Student create() {
        ioService.print(localizationService.getMessage("inputFirstName") + " \n");
        String firstName = ioService.nextLine();
        ioService.print(localizationService.getMessage("inputLastName") + " \n");
        String lastName = ioService.nextLine();
        return new Student(lastName, firstName, 0);
    }

}
