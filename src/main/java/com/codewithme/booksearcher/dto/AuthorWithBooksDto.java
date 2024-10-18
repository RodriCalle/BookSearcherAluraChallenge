package com.codewithme.booksearcher.dto;

import java.util.List;

public record AuthorWithBooksDto(
        String name,
        int birthYear,
        int deathYear,
        List<BookDto> books
) {
}
