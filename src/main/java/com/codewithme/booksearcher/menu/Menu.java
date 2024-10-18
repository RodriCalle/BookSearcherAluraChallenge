package com.codewithme.booksearcher.menu;

import com.codewithme.booksearcher.model.Language;
import com.codewithme.booksearcher.service.IBookService;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    private IBookService bookService;

    public Menu(IBookService bookService) {
        this.bookService = bookService;
    }

    public void showMenu() {
        int opcion = -1;
        var menu = """
                1.- Buscar libro por titulo
                2.- Listar libros registrados
                3.- Listar autores registrados
                4.- Listar autores vivos en un determinado año
                5.- Listar libros por idioma
                0.- Salir
                """;
        while (opcion != 0) {
            System.out.println(menu);
            System.out.println("Ingresa tu opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                opcion = -1;
                System.out.println(e.getMessage());
            }

            switch (opcion) {
                case 1:
                    searchBooksByTitle();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    listAliveAuthorsByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("La opción elegida es incorrecta");
                    break;
            }
        }
    }

    private void listBooksByLanguage() {
        System.out.println("Ingrese el idioma: ");
        var language = scanner.nextLine();

        try {
            bookService.getBooksByLanguage(Language.fromString(language))
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void listAliveAuthorsByYear() {
        System.out.println("Ingrese el año: ");
        var year = scanner.nextInt();

        var authors = bookService.getAliveAuthorsByYear(year);
        authors.forEach(System.out::println);
    }

    private void listAllAuthors() {
        var authors = bookService.getAllAuthors();
        authors.forEach(System.out::println);
    }

    private void listAllBooks() {
        var books = bookService.getAllBooks();
        books.forEach(System.out::println);
    }

    private void searchBooksByTitle() {
        System.out.println("Ingrese el titulo del libro: ");
        var title = scanner.nextLine();
        var bookDto = bookService.getBookByTitle(title);
        if (bookDto != null) {
            System.out.println(bookDto);
        }
    }
}
