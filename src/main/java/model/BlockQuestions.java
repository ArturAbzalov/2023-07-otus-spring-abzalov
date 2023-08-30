package model;

import lombok.Data;

import java.util.Map;

@Data
public class BlockQuestions {

    private String question;

    private Map<Integer, String> answers;

    private String trueAnswer;

    public static BlockQuestionsBuilder builder() {
        return new BlockQuestionsBuilder();
    }

    public static class BlockQuestionsBuilder {
        private final BlockQuestions blockQuestions;

        public BlockQuestionsBuilder() {
            blockQuestions = new BlockQuestions();
        }

        public BlockQuestionsBuilder question(String question) {
            blockQuestions.question = question;
            return this;
        }

        public BlockQuestionsBuilder answers(Map<Integer, String> answers) {
            blockQuestions.answers = answers;
            return this;
        }

        public BlockQuestionsBuilder trueAnswer(String trueAnswer) {
            blockQuestions.trueAnswer = trueAnswer;
            return this;
        }

        public BlockQuestions build() {
            return this.blockQuestions;
        }
    }
}
