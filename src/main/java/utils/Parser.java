package utils;

import model.BlockQuestions;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    public List<BlockQuestions> getBlockQuestions(InputStream inputStream) {
        List<BlockQuestions> blockQuestionsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                BlockQuestions blockQuestions = BlockQuestions.builder()
                        .question(strings[0])
                        .answers(new HashMap<>() {{
                            this.put(1, strings[1]);
                            this.put(2, strings[2]);
                            this.put(3, strings[3]);
                            this.put(4, strings[4]);
                        }})
                        .trueAnswer(strings[5])
                        .build();
                blockQuestionsList.add(blockQuestions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blockQuestionsList;
    }
}
