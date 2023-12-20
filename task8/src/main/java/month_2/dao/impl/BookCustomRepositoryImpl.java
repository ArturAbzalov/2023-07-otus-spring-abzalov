package month_2.dao.impl;

import lombok.RequiredArgsConstructor;
import month_2.dao.BookCustomRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void cascadeDeleteById(String id) {
        mongoTemplate.remove(query(where("book").is(new ObjectId(id))),"comments");
        mongoTemplate.remove(query(where("_id").is(id)),"books");
    }
}
