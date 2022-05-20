package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;


import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Record;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("AuthorService")
public class AuthorService {

    private AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = false)
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Boolean existsByFirstNameAndLastName(String firstname, String lastname){
        return authorRepository.existsByFirstNameAndLastName(firstname, lastname);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {RuntimeException.class},
            noRollbackFor = {NullPointerException.class},
            rollbackForClassName = {"RuntimeException"},
            noRollbackForClassName = {"NullPointerException"}

    )
    public void save(Author author){
        authorRepository.save(author);
    }
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = {RuntimeException.class})
    public void delete(Author author){
        authorRepository.delete(author);
    }
    @Transactional(readOnly = false)
    public Optional<Author> findById(Long id){
        return authorRepository.findById(id);
    }
}
