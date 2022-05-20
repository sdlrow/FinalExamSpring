package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Record;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.AuthorRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("BookCategoryService")
public class BookCategoryService {

    BookCategoryRepository bookCategoryRepository;
    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @Transactional(readOnly = false)
    public List<BookCategory> findAll(){
        return bookCategoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Book> findByTitle(String title){
        return bookCategoryRepository.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public Boolean existsByTitle(String title){
        return bookCategoryRepository.existsByTitle(title);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {RuntimeException.class},
            noRollbackFor = {NullPointerException.class},
            rollbackForClassName = {"RuntimeException"},
            noRollbackForClassName = {"NullPointerException"}

    )
    public void save(BookCategory bookCategory){
        bookCategoryRepository.save(bookCategory);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = {RuntimeException.class})
    public void delete(BookCategory bookCategory){
        bookCategoryRepository.delete(bookCategory);
    }

    @Transactional(readOnly = false)
    public Optional<BookCategory> findById(Long id){
        return bookCategoryRepository.findById(id);
    }
}
