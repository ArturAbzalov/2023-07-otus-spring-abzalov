package dao;

import model.BlockQuestions;

import java.util.List;

public interface FileDao {
    List<BlockQuestions> getBlockQuestions();
}
