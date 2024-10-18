package com.codewithme.booksearcher.dto;

import com.codewithme.booksearcher.model.Author;
import com.codewithme.booksearcher.model.Language;

public record BookDto(
        String title,
        AuthorDto author,
        Language language,
        long downloadCount
) {
}
