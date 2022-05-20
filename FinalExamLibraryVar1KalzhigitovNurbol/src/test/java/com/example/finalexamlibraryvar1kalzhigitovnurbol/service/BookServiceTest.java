package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Genre;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByTitle() {
        when(bookRepository.findByTitle(anyString())).thenReturn(null);

        Optional<Book> result = bookService.findByTitle("title");
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindAll() {
        when(bookRepository.findAll()).thenReturn(Arrays.<Book>asList(new Book("title", "description", "imageURL", "publication", Integer.valueOf(0), "binding", new HashSet<Genre>(Arrays.asList(new Genre("title", "description"))), new HashSet<Author>(Arrays.asList(new Author("firstName", "lastName"))), new BookCategory("title", "description"), Double.valueOf(0))));

        List<Book> result = bookService.findAll();
        Assertions.assertEquals(Arrays.<Book>asList(new Book("title", "description", "imageURL", "publication", Integer.valueOf(0), "binding", new HashSet<Genre>(Arrays.asList(new Genre("title", "description"))), new HashSet<Author>(Arrays.asList(new Author("firstName", "lastName"))), new BookCategory("title", "description"), Double.valueOf(0))), result);
    }

    @Test
    void testExistsByTitle() {
        when(bookRepository.existsByTitle(anyString())).thenReturn(Boolean.TRUE);

        Boolean result = bookService.existsByTitle("title");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testFindByBookCategory() {
        when(bookRepository.findByBookCategory(any())).thenReturn(null);

        Optional<Book> result = bookService.findByBookCategory(new BookCategory("title", "description"));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindById() {
        when(bookRepository.findById(anyLong())).thenReturn(null);

        Optional<Book> result = bookService.findById(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testSave() {
        bookService.save(new Book("title", "description", "imageURL", "publication", Integer.valueOf(0), "binding", new HashSet<Genre>(Arrays.asList(new Genre("title", "description"))), new HashSet<Author>(Arrays.asList(new Author("firstName", "lastName"))), new BookCategory("title", "description"), Double.valueOf(0)));
    }

    @Test
    void testDelete() {
        bookService.delete(new Book("title", "description", "imageURL", "publication", Integer.valueOf(0), "binding", new HashSet<Genre>(Arrays.asList(new Genre("title", "description"))), new HashSet<Author>(Arrays.asList(new Author("firstName", "lastName"))), new BookCategory("title", "description"), Double.valueOf(0)));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme