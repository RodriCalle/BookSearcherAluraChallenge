package com.codewithme.booksearcher;

import com.codewithme.booksearcher.menu.Menu;
import com.codewithme.booksearcher.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookSearcherApplication implements CommandLineRunner {

    @Autowired
    private IBookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookSearcherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Menu menu = new Menu(bookService);
        menu.showMenu();
    }
}
