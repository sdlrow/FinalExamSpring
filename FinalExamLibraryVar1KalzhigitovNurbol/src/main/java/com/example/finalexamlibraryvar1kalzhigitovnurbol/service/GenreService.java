package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Genre;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("GenreService")
public class GenreService {

    private GenreRepository genreRepository;
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = false)
    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Boolean existsByTitle(String title){
        return genreRepository.existsByTitle(title);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {RuntimeException.class},
            noRollbackFor = {NullPointerException.class},
            rollbackForClassName = {"RuntimeException"},
            noRollbackForClassName = {"NullPointerException"}

    )
    public void save(Genre genre){
        genreRepository.save(genre);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = {RuntimeException.class})
    public void delete(Genre genre){
        genreRepository.delete(genre);
    }

    @Transactional(readOnly = false)
    public Optional<Genre> findById(Long id){
        return genreRepository.findById(id);
    }
}