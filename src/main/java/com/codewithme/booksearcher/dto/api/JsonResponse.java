package com.codewithme.booksearcher.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonResponse(
        int count,
        String next,
        String previous,
        List<BookData> results
) {
}
