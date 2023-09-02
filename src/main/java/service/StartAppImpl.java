package service;

import dao.BlockQuestionDao;
import model.BlockQuestion;

import java.text.MessageFormat;
import java.util.List;

public class StartAppImpl implements StartApp {

    private final BlockQuestionDao blockQuestionDao;

    private final IOService ioService;

    public StartAppImpl(BlockQuestionDao blockQuestionDao, IOService ioService) {
        this.blockQuestionDao = blockQuestionDao;
        this.ioService = ioService;
    }

    public void getStart() {
        List<BlockQuestion> blockQuestionList = blockQuestionDao.getBlockQuestions();
        workOfConsole(blockQuestionList);
    }

    private void workOfConsole(List<BlockQuestion> blockQuestionList) {
        int balls = 0;
        ioService.print("Введите свое имя: ");
        String name = ioService.nextLine();
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
        if (balls >= 3) {
            ioService.print(MessageFormat.format("Great job, {0}! Your scores: {1}", name, balls));
        } else {
            ioService.print(MessageFormat.format("Sorry, {0}...try again. Your scores: {1}", name, balls));
        }
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
}
