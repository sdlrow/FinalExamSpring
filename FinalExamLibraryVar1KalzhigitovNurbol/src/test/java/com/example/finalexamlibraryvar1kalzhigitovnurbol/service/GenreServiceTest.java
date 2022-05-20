package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Genre;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.GenreRepository;
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

class GenreServiceTest {
    @Mock
    GenreRepository genreRepository;
    @InjectMocks
    GenreService genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        when(genreRepository.findAll()).thenReturn(Arrays.<Genre>asList(new Genre("title", "description")));

        List<Genre> result = genreService.findAll();
        Assertions.assertEquals(Arrays.<Genre>asList(new Genre("title", "description")), result);
    }

    @Test
    void testExistsByTitle() {
        when(genreRepository.existsByTitle(anyString())).thenReturn(Boolean.TRUE);

        Boolean result = genreService.existsByTitle("title");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testSave() {
        genreService.save(new Genre("title", "description"));
    }

    @Test
    void testDelete() {
        genreService.delete(new Genre("title", "description"));
    }

    @Test
    void testFindById() {
        when(genreRepository.findById(anyLong())).thenReturn(null);

        Optional<Genre> result = genreService.findById(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme