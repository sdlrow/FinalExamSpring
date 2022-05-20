package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.BookCategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class BookCategoryServiceTest {
    @Mock
    BookCategoryRepository bookCategoryRepository;
    @InjectMocks
    BookCategoryService bookCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        when(bookCategoryRepository.findAll()).thenReturn(Arrays.<BookCategory>asList(new BookCategory("title", "description")));

        List<BookCategory> result = bookCategoryService.findAll();
        Assertions.assertEquals(Arrays.<BookCategory>asList(new BookCategory("title", "description")), result);
    }

    @Test
    void testFindByTitle() {
        when(bookCategoryRepository.findByTitle(anyString())).thenReturn(null);

        Optional<Book> result = bookCategoryService.findByTitle("title");
        Assertions.assertEquals(null, result);
    }

    @Test
    void testExistsByTitle() {
        when(bookCategoryRepository.existsByTitle(anyString())).thenReturn(Boolean.TRUE);

        Boolean result = bookCategoryService.existsByTitle("title");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testSave() {
        bookCategoryService.save(new BookCategory("title", "description"));
    }

    @Test
    void testDelete() {
        bookCategoryService.delete(new BookCategory("title", "description"));
    }

    @Test
    void testFindById() {
        when(bookCategoryRepository.findById(anyLong())).thenReturn(null);

        Optional<BookCategory> result = bookCategoryService.findById(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme