package com.codewithme.booksearcher.dto;

import com.codewithme.booksearcher.model.Language;

public record BookWithAuthorDto(
        String title,
        AuthorDto author,
        Language language,
        long downloadCount
) {
}
