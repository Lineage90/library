package com.example.library.controller;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponseDto addBook(@RequestBody BookRequestDto requestDto) {
        Book book = new Book();
        BeanUtils.copyProperties(requestDto, book);
        Book savedBook = bookService.saveBook(book);

        BookResponseDto responseDto = new BookResponseDto();
        BeanUtils.copyProperties(savedBook, responseDto);
        return responseDto;
    }

    @GetMapping
    public List<BookResponseDto> getAllBooksSortedByRegistrationDate() {
        List<Book> books = bookService.getAllBooksSortedByRegistrationDate();
        return books.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<BookResponseDto> getBookById(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.getBookById(id);
        return optionalBook.map(this::convertToResponseDto);
    }

    private BookResponseDto convertToResponseDto(Book book) {
        BookResponseDto responseDto = new BookResponseDto();
        BeanUtils.copyProperties(book, responseDto);
        return responseDto;
    }
    @PostMapping("/{bookId}/borrow/{memberId}")
    public String borrowBook(@PathVariable Long bookId, @PathVariable Long memberId) {
        if (bookService.borrowBook(bookId, memberId)) {
            return "대출 성공";
        } else {
            return "대출 실패";
        }
    }

    @PostMapping("/{bookId}/return/{memberId}")
    public String returnBook(@PathVariable Long bookId, @PathVariable Long memberId) {
        if (bookService.returnBook(bookId, memberId)) {
            return "반납 성공";
        } else {
            return "반납 실패";
        }
    }
}