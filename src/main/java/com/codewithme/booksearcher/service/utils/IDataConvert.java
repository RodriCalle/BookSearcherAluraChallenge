package com.codewithme.booksearcher.service.utils;

public interface IDataConvert {
    <T> T getData(String json, Class<T> clazz);
}
