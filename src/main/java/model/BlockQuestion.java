package model;

import lombok.Data;

import java.util.Map;

@Data
public class BlockQuestion {

    private String question;

    private Map<Integer, Answer> answers;

    private String trueAnswer;

    public static BlockQuestionsBuilder builder() {
        return new BlockQuestionsBuilder();
    }

    public static class BlockQuestionsBuilder {
        private final BlockQuestion blockQuestion;

        public BlockQuestionsBuilder() {
            blockQuestion = new BlockQuestion();
        }

        public BlockQuestionsBuilder question(String question) {
            blockQuestion.question = question;
            return this;
        }

        public BlockQuestionsBuilder answers(Map<Integer, Answer> answers) {
            blockQuestion.answers = answers;
            return this;
        }

        public BlockQuestionsBuilder trueAnswer(String trueAnswer) {
            blockQuestion.trueAnswer = trueAnswer;
            return this;
        }

        public BlockQuestion build() {
            return this.blockQuestion;
        }
    }
}
