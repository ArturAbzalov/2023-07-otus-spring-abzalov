package month_1.service;

import month_1.dao.BlockQuestionDao;
import month_1.dao.StudentDao;
import month_1.model.BlockQuestion;
import month_1.model.Student;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
public class StartAppImpl implements StartApp {

    private final BlockQuestionDao blockQuestionDao;

    private final IOService ioService;

    private final StudentDao studentDao;

    private final LocalizationService localizationService;

    public StartAppImpl(BlockQuestionDao blockQuestionDao, IOService ioService, StudentDao studentDao,
                        LocalizationService localizationService) {
        this.blockQuestionDao = blockQuestionDao;
        this.ioService = ioService;
        this.studentDao = studentDao;
        this.localizationService = localizationService;
    }

    public void getStart() {
        List<BlockQuestion> blockQuestionList = blockQuestionDao.getBlockQuestions();
        Student student = workOfConsole(blockQuestionList);
        String result = checkBalls(student);
        ioService.print(result);
    }

    private Student workOfConsole(List<BlockQuestion> blockQuestionList) {
        int balls = 0;
        Student student = studentDao.create();
        for (BlockQuestion blockQuestion : blockQuestionList) {
            printMainBlock(blockQuestion);
            do {
                try {
                    balls += checkAnswer(blockQuestion, Integer.parseInt(ioService.nextLine()));
                    break;
                } catch (Exception e) {
                    ioService.print(MessageFormat.format(localizationService.getMessage("warningInputNumber") + " \n",
                            blockQuestion.getAnswers().keySet().size()));
                }
            } while (true);
            ioService.print(localizationService.getMessage("registeredAnswer") + " \n");
        }
        student.setBalls(balls);
        return student;
    }


    private int checkAnswer(BlockQuestion blockQuestion, int numberAnswer) {
        if (blockQuestion.getAnswers().get(numberAnswer).isTrue()) {
            return 1;
        }
        return 0;
    }

    private void printMainBlock(BlockQuestion blockQuestion) {
        ioService.print(blockQuestion.getQuestion() + "\n");
        blockQuestion.getAnswers().forEach((key, answer) -> {
            ioService.print(MessageFormat.format("{0}.", key));
            ioService.print(answer.getContext() + "\n");
        });
        ioService.print(localizationService.getMessage("inputNumberAnswer"));
    }

    private String checkBalls(Student student) {
        if (student.getBalls() >= 3) {
            return MessageFormat.format(localizationService.getMessage("success"), student, student.getBalls());
        } else {
            return MessageFormat.format(localizationService.getMessage("wasted"), student, student.getBalls());
        }
    }
}
