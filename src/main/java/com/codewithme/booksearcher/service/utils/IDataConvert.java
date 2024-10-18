package com.codewithme.booksearcher.service.utils;

public interface IConvertData {
    <T> T getData(String json, Class<T> clazz);
}
