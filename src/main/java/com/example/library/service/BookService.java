package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Loan;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public BookService(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooksSortedByRegistrationDate() {
        return bookRepository.findAllByOrderByRegistrationDateAsc();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    public boolean borrowBook(Long bookId, Long memberId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            if (!book.isBorrowed()) {
                // 도서가 현재 대출 중이 아니라면 대출 처리
                book.setBorrowed(true);
                bookRepository.save(book);
                return true;
            } else {
                // 도서가 이미 대출 중인 경우
                return false;
            }
        } else {
            // 해당 식별값의 도서가 존재하지 않는 경우
            return false;
        }
    }
    public boolean returnBook(Long bookId, Long memberId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            if (book.isBorrowed()) {
                // 도서가 대출 중인 경우 반납 처리
                book.setBorrowed(false);
                bookRepository.save(book);

                // 대출 내역 조회
                List<Loan> loans = loanRepository.findByMemberIdAndReturnStatus(memberId, false);

                for (Loan loan : loans) {
                    if (loan.getBookId().equals(bookId)) {
                        loan.setReturnStatus(true); // 반납 완료
                        loan.setReturnDate(new Date());
                        loanRepository.save(loan);
                        return true;
                    }
                }

                // 대출 내역이 없거나 해당 도서에 대한 대출 내역이 없는 경우
                return false;
            } else {
                // 도서가 이미 반납된 경우
                return false;
            }
        } else {
            // 해당 식별값의 도서가 존재하지 않는 경우
            return false;
        }
    }
}
