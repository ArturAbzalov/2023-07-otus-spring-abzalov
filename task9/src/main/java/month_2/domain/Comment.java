package month_2.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.ConstraintMode.CONSTRAINT;

@Getter
@Builder
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
//@NamedEntityGraph(name = "comment-entity-graph")
//,
//        attributeNodes = @NamedAttributeNode(value = "book", subgraph = "book.genre.author"),
//        subgraphs = {
//@NamedSubgraph(name = "book.genre.author",
//        attributeNodes = {
//                @NamedAttributeNode("author"), @NamedAttributeNode("genre")
//        })}
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id"
            ,foreignKey = @ForeignKey(value = CONSTRAINT,
                    foreignKeyDefinition = "FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE"))
    private Book book;

    @Column(name = "message")
    private String message;

}
