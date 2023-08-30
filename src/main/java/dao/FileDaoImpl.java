package dao;

import model.BlockQuestions;
import org.springframework.core.io.Resource;
import utils.Parser;

import java.io.InputStream;
import java.util.List;

public class FileDaoImpl implements FileDao {
    private final Resource resource;

    private final Parser parser;

    public FileDaoImpl(Resource resource, Parser parser) {
        this.parser = parser;
        this.resource = resource;
    }

    @Override
    public List<BlockQuestions> getBlockQuestions() {
        List<BlockQuestions> list = null;
        try (InputStream file = resource.getInputStream()) {
            list = parser.getBlockQuestions(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
