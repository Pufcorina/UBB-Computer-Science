package ro.ubb.core.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "person")
@NamedEntityGraph(name = "authorWithBooks",
        attributeNodes = @NamedAttributeNode(value = "books",subgraph = "bookWithAuthor"),
        subgraphs = @NamedSubgraph(name="bookWithAuthor", attributeNodes = @NamedAttributeNode(value="author")))
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author extends Person implements Serializable {

    @Column(name = "publisher")
    private String publisher;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;


}
