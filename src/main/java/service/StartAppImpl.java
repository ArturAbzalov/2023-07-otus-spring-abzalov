package service;

import dao.FileDao;
import model.BlockQuestions;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

public class StartAppImpl implements StartApp {

    private final FileDao fileDao;

    public StartAppImpl(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public void getStart() {
        List<BlockQuestions> blockQuestionsList = fileDao.getBlockQuestions();
        try (Scanner scanner = new Scanner(System.in)) {
            workOfConsole(blockQuestionsList, scanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void workOfConsole(List<BlockQuestions> blockQuestionsList, Scanner scanner) {
        int balls = 0;
        System.out.print("Введите свое имя: ");
        String name = scanner.nextLine();
        for (BlockQuestions blockQuestions : blockQuestionsList) {
            printMainBlock(blockQuestions);
            int numberAnswer;
            do {
                try {
                    numberAnswer = scanner.nextInt();
                    balls += checkAnswer(blockQuestions, numberAnswer);
                    break;
                } catch (Exception e) {
                    System.out.printf("Введите число от 1 до %d: ", blockQuestions.getAnswers().keySet().size());
                    scanner.next();
                }
            } while (true);
            System.out.println("Ваш ответ принят");
        }
        if (balls >= 3) {
            System.out.printf("Great job, %s! Your scores: %d", name, balls);
        } else {
            System.out.printf("Sorry, %s...try again. Your scores: %d", name, balls);
        }
    }


    private int checkAnswer(BlockQuestions blockQuestions, int numberAnswer) {
        if (blockQuestions.getAnswers().get(numberAnswer)
                .equalsIgnoreCase(blockQuestions.getTrueAnswer())) {
            return 1;
        }
        return 0;
    }

    private void printMainBlock(BlockQuestions blockQuestions) {
        System.out.println(blockQuestions.getQuestion());
        blockQuestions.getAnswers().forEach((key, value) -> {
            System.out.print(MessageFormat.format("{0}.", key));
            System.out.println(value);
        });
        System.out.print("Enter your number answer: ");
    }
}
