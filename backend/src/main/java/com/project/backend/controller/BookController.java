package com.project.backend.controller;

import com.project.backend.model.Book;
import com.project.backend.model.Reservation;
import com.project.backend.model.Transaction;
import com.project.backend.model.User;
import com.project.backend.repository.BookRepository;
import com.project.backend.repository.ReservationRepository;
import com.project.backend.repository.TransactionRepository;
import com.project.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/save")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PostMapping("/{bookId}/borrow/{userId}")
    public String borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (bookOptional.isPresent() && userOptional.isPresent()) {
            Book book = bookOptional.get();
            User user = userOptional.get();

            if ("BORROWED".equals(book.getStatus())) {
                return "Book already borrowed";
            }

            book.setStatus("BORROWED");
            book.setBorrowedBy(user);
            bookRepository.save(book);

            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setBook(book);
            transaction.setBorrowDate(LocalDateTime.now());
            transactionRepository.save(transaction);

            return "Book borrowed successfully!";
        }
        return "Book not found!";
    }

    @PostMapping("/{bookId}/reserve/{userId}")
    public String reserveBook(@PathVariable Long bookId, @PathVariable Long userId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (bookOptional.isPresent() && userOptional.isPresent()) {
            Book book = bookOptional.get();
            User user = userOptional.get();

            if (!"BORROWED".equals(book.getStatus())) {
                return "Book is available. No need to reserve.";
            }

            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setBook(book);
            reservation.setStatus("PENDING");
            reservation.setReservedDate(LocalDateTime.now());

            reservationRepository.save(reservation);
        }
        return "Book not found!";
    }

}
