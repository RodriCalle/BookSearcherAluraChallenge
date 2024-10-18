package com.codewithme.booksearcher.service;

import com.codewithme.booksearcher.dto.AuthorDto;
import com.codewithme.booksearcher.dto.AuthorWithBooksDto;
import com.codewithme.booksearcher.dto.BookDto;
import com.codewithme.booksearcher.dto.BookWithAuthorDto;
import com.codewithme.booksearcher.model.Author;
import com.codewithme.booksearcher.model.Book;
import com.codewithme.booksearcher.model.Language;

import java.util.List;

public interface IBookService {
    BookDto bookToBookDto(Book book);
    BookWithAuthorDto bookToBookWithAuthorDto(Book book);
    AuthorDto authorToAuthorDto(Author author);
    AuthorWithBooksDto authorToAuthorWithBooksDto(Author author);

    BookWithAuthorDto getBookByTitle(String title);
    List<BookWithAuthorDto> getAllBooks();
    List<AuthorWithBooksDto> getAllAuthors();
    List<BookWithAuthorDto> getBooksByLanguage(Language language);

    List<AuthorWithBooksDto> getAliveAuthorsByYear(int year);
}
