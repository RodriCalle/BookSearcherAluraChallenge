package com.codewithme.booksearcher.dto;

public record AuthorDto(
        String name,
        int birthYear,
        int deathYear
) {
}
