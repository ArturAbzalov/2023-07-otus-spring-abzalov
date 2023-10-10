package month_1.dao;

import month_1.config.LocaleConfig;
import month_1.exception.ParseException;
import month_1.model.Answer;
import month_1.model.BlockQuestion;
import month_1.service.LocalizationService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CsvBlockQuestionDaoImpl implements BlockQuestionDao {

    private final LocaleConfig localeConfigImpl;

    private final LocalizationService localizationService;

    public CsvBlockQuestionDaoImpl(LocaleConfig localeConfigImpl, LocalizationService localizationService) {
        this.localeConfigImpl = localeConfigImpl;
        this.localizationService = localizationService;
    }

    public List<BlockQuestion> getBlockQuestions() throws ParseException {
        List<BlockQuestion> blockQuestionList = new ArrayList<>();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(
                             new ClassPathResource(localeConfigImpl.getPath()).getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                BlockQuestion blockQuestion = BlockQuestion
                        .builder()
                        .question(strings[0])
                        .answers(new HashMap<>() {{
                            this.put(1, new Answer(strings[1], strings[1].equalsIgnoreCase(strings[5])));
                            this.put(2, new Answer(strings[2], strings[2].equalsIgnoreCase(strings[5])));
                            this.put(3, new Answer(strings[3], strings[3].equalsIgnoreCase(strings[5])));
                            this.put(4, new Answer(strings[4], strings[4].equalsIgnoreCase(strings[5])));
                        }})
                        .build();
                blockQuestionList.add(blockQuestion);
            }
        } catch (IOException e) {
            throw new ParseException(localizationService.getMessage("exceptionParse"), e);
        }
        return blockQuestionList;
    }
}
