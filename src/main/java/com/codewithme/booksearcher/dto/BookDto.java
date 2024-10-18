package com.codewithme.booksearcher.dto;

import com.codewithme.booksearcher.model.Language;

public record BookDto(
        String title,
        Language language,
        long downloadCount
) {
}
