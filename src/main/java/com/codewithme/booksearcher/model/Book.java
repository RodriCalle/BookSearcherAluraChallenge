package com.codewithme.booksearcher.model;

import com.codewithme.booksearcher.dto.api.BookData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Column(unique = true, nullable = false)
    private String title;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @Enumerated(value = EnumType.STRING)
    private Language language;
    private long downloadCount;

    public Book(BookData bookData) {
        title = bookData.title();
        language = Language.fromString(bookData.languages().get(0));
        downloadCount = bookData.downloadCount();
    }
}
