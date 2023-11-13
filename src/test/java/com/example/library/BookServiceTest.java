package com.example.library;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testBookRegistration() {
        // Given
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setLanguage("English");
        book.setPublisher("Test Publisher");


        Book registeredBook = bookService.saveBook(book);


        assertEquals("Test Book", registeredBook.getTitle());

    }
    @Test
    public void testGetAllBooks() {

        List<Book> book = bookService.getAllBooksSortedByRegistrationDate();
    }
    @Test
    public void testBookBorrowAndReturn() {
        // Given
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setLanguage("English");
        book.setPublisher("Test Publisher");


        Book registeredBook = bookService.saveBook(book);


        assertTrue(bookService.borrowBook(registeredBook.getId(), memberId));
        // Add assertions for borrowed book

        assertTrue(bookService.returnBook(registeredBook.getId(), memberId));
        // Add assertions for returned book
    }

}