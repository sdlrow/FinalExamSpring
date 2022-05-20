package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.*;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.AuthorRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.BookCategoryRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.PenaltyRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("RecordService")
public class RecordService {

    RecordRepository recordRepository;
    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Record> findById(Long id){
        return recordRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public Optional<Record> findRecordByUser(User user){
        return recordRepository.findRecordByUser(user);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {RuntimeException.class},
            noRollbackFor = {NullPointerException.class},
            rollbackForClassName = {"RuntimeException"},
            noRollbackForClassName = {"NullPointerException"}

    )
    public void save(Record record){
        recordRepository.save(record);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = {RuntimeException.class})
    public void delete(Record record){
        recordRepository.delete(record);
    }
}
