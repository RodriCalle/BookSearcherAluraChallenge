package com.codewithme.booksearcher.model;

public enum Language {
    SPANISH("es", "español", "spanish"),
    ENGLISH("en", "ingles", "english"),
    FRANCE("fr", "frances", "france"),
    PORTUGUESE("pt", "portugues", "portuguese");

    private String languageCode;
    private String languageSpanish;
    private String languageEnglish;

    Language(String languageCode, String languageSpanish, String languageEnglish) {
        this.languageCode = languageCode;
        this.languageSpanish = languageSpanish;
        this.languageEnglish = languageEnglish;
    }

    public static Language fromString(String languageCode) {
        for (Language lang : Language.values()) {
            if (lang.languageCode.equalsIgnoreCase(languageCode)
            || lang.languageSpanish.equalsIgnoreCase(languageCode)
            || lang.languageEnglish.equalsIgnoreCase(languageCode) ) {
                return lang;
            }
        }
        throw new IllegalArgumentException("Lenguaje no encontrado: " + languageCode);
    }
}
