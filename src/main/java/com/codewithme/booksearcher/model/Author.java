package com.codewithme.booksearcher.model;

import com.codewithme.booksearcher.dto.api.AuthorData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int birthYear;
    private int deathYear;

    @OneToMany(
            targetEntity = Book.class, cascade = CascadeType.ALL,
        fetch = FetchType.EAGER, mappedBy = "author"
    )
    private List<Book> books;

    public Author(AuthorData authorData) {
        this.name = authorData.name();
        this.birthYear = authorData.birthYear();
        this.deathYear = authorData.deathYear();
    }
}
