package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.*;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.AuthorRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.BookCategoryRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.PenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("PenaltyService")
public class PenaltyService {

    PenaltyRepository penaltyRepository;
    @Autowired
    public PenaltyService(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    @Transactional(readOnly = false)
    public List<Penalty> getByUser(User user){
        return penaltyRepository.getByUser(user);
    }

    @Transactional(readOnly = false)
    public Optional<Penalty> findById(Long id){
        return penaltyRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public List<Penalty> findAll(){
        return penaltyRepository.findAll();
    }

    public void save(Penalty penalty){
        penaltyRepository.save(penalty);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = {RuntimeException.class})
    public void delete(Penalty penalty){
        penaltyRepository.delete(penalty);
    }

}
