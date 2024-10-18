package com.codewithme.booksearcher.service;

import com.codewithme.booksearcher.dto.AuthorDto;
import com.codewithme.booksearcher.dto.AuthorWithBooksDto;
import com.codewithme.booksearcher.dto.BookDto;
import com.codewithme.booksearcher.dto.BookWithAuthorDto;
import com.codewithme.booksearcher.dto.api.JsonResponse;
import com.codewithme.booksearcher.model.Author;
import com.codewithme.booksearcher.model.Book;
import com.codewithme.booksearcher.model.Language;
import com.codewithme.booksearcher.repository.IAuthorRepository;
import com.codewithme.booksearcher.repository.IBookRepository;
import com.codewithme.booksearcher.service.utils.ApiConsume;
import com.codewithme.booksearcher.service.utils.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    private final DataConvert dataConvert = new DataConvert();
    private final ApiConsume apiConsume = new ApiConsume();

    private final String API_BASE_URL = "https://gutendex.com/";

    @Autowired
    IBookRepository bookRepository;
    @Autowired
    IAuthorRepository authorRepository;

    @Override
    public BookDto bookToBookDto(Book book) {
        return new BookDto(book.getTitle(), book.getLanguage(), book.getDownloadCount());
    }

    @Override
    public BookWithAuthorDto bookToBookWithAuthorDto(Book book) {
        return new BookWithAuthorDto(
                book.getTitle(),
                authorToAuthorDto(book.getAuthor()),
                book.getLanguage(),
                book.getDownloadCount()
        );
    }

    @Override
    public AuthorDto authorToAuthorDto(Author author) {
        return new AuthorDto(author.getName(), author.getBirthYear(), author.getDeathYear());
    }

    @Override
    public AuthorWithBooksDto authorToAuthorWithBooksDto(Author author) {
        return new AuthorWithBooksDto(
                author.getName(),
                author.getBirthYear(),
                author.getDeathYear(),
                author.getBooks().stream().map(this::bookToBookDto).toList()
        );
    }

    @Override
    public BookWithAuthorDto getBookByTitle(String title) {
        var json = apiConsume.getData(API_BASE_URL + "books/?search=" + title.replaceAll(" ", "%20"));
        var result = dataConvert.getData(json, JsonResponse.class);

        if (result.results().isEmpty()) {
            System.out.println("El libro no fue encontrado");
            return null;
        }

        var bookData = result.results().stream().findFirst().get();
        var firstAuthor = bookData.authors().get(0);
        var author = authorRepository
                .findAuthorByNameIgnoreCase(firstAuthor.name())
                .orElseGet(() -> authorRepository.save(new Author(firstAuthor)));

        try {
            Book book = new Book(bookData);
            book.setAuthor(author);
            bookRepository.save(book);
            return bookToBookWithAuthorDto(book);
        } catch (Exception e) {
            System.out.println("El libro ya se encuentra registrado.");
            return null;
        }
    }

    @Override
    public List<BookWithAuthorDto> getAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(this::bookToBookWithAuthorDto)
                .toList();
    }

    @Override
    public List<AuthorWithBooksDto> getAllAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(this::authorToAuthorWithBooksDto)
                .toList();
    }

    @Override
    public List<BookWithAuthorDto> getBooksByLanguage(Language language) {
        return bookRepository.findAllByLanguage(language)
                .stream()
                .map(this::bookToBookWithAuthorDto)
                .toList();
    }

    @Override
    public List<AuthorWithBooksDto> getAliveAuthorsByYear(int year) {
        return authorRepository.findAllByBirthYearLessThanEqualAndDeathYearGreaterThan(year, year)
                .stream().map(this::authorToAuthorWithBooksDto)
                .toList();
    }
}