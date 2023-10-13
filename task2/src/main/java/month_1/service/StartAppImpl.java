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

    public StartAppImpl(BlockQuestionDao blockQuestionDao, IOService ioService, StudentDao studentDao) {
        this.blockQuestionDao = blockQuestionDao;
        this.ioService = ioService;
        this.studentDao = studentDao;
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
                    ioService.print(MessageFormat.format("Введите число от 1 до {0}: \n",
                            blockQuestion.getAnswers().keySet().size()));
                }
            } while (true);
            ioService.print("Ваш ответ принят" + "\n");
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
        ioService.print("Enter your number answer: ");
    }

    private String checkBalls(Student student) {
        if (student.getBalls() >= 3) {
            return MessageFormat.format("Great job, {0}! Your scores: {1}", student, student.getBalls());
        } else {
            return MessageFormat.format("Sorry, {0}...try again. Your scores: {1}", student, student.getBalls());
        }
    }
}
