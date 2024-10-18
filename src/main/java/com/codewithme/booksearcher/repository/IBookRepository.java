package com.codewithme.booksearcher.repository;

import com.codewithme.booksearcher.model.Book;
import com.codewithme.booksearcher.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByLanguage(Language language);
}
