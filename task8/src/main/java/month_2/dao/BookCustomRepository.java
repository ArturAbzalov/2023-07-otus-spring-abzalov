package month_2.dao;

public interface BookCustomRepository {

    void cascadeDeleteById(String id);
}
