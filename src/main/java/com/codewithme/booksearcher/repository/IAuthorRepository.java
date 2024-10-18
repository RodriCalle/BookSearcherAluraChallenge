package com.codewithme.booksearcher.repository;

import com.codewithme.booksearcher.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByNameIgnoreCase(String name);
    List<Author> findAllByBirthYearLessThanEqualAndDeathYearGreaterThan(int year, int year2);
}
