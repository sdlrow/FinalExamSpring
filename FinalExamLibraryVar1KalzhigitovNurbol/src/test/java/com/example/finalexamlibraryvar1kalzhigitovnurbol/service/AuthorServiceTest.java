package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.AuthorRepository;
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

class AuthorServiceTest {
    @Mock
    AuthorRepository authorRepository;
    @InjectMocks
    AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        when(authorRepository.findAll()).thenReturn(Arrays.<Author>asList(new Author("firstName", "lastName")));

        List<Author> result = authorService.findAll();
        Assertions.assertEquals(Arrays.<Author>asList(new Author("firstName", "lastName")), result);
    }

    @Test
    void testExistsByFirstNameAndLastName() {
        when(authorRepository.existsByFirstNameAndLastName(anyString(), anyString())).thenReturn(Boolean.TRUE);

        Boolean result = authorService.existsByFirstNameAndLastName("firstname", "lastname");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testSave() {
        authorService.save(new Author("firstName", "lastName"));
    }

    @Test
    void testDelete() {
        authorService.delete(new Author("firstName", "lastName"));
    }

    @Test
    void testFindById() {
        when(authorRepository.findById(anyLong())).thenReturn(null);

        Optional<Author> result = authorService.findById(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme