package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;


import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("BookService")
public class BookService {

    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Transactional(readOnly = true)
    public Optional<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    @Transactional(readOnly = false)
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Boolean existsByTitle(String title){
        return bookRepository.existsByTitle(title);
    }

    @Transactional(readOnly = true)
    public Optional<Book> findByBookCategory(BookCategory bookCategory){
        return bookRepository.findByBookCategory(bookCategory);
    }
    @Transactional(readOnly = false)
    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }
    @Transactional(isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {RuntimeException.class},
            noRollbackFor = {NullPointerException.class},
            rollbackForClassName = {"RuntimeException"},
            noRollbackForClassName = {"NullPointerException"}

    )
    public void save(Book book){
        bookRepository.save(book);
    }
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = {RuntimeException.class})
    public void delete(Book book){
        bookRepository.delete(book);
    }

}

