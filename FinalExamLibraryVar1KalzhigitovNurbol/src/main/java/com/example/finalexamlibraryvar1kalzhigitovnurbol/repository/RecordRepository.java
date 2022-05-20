package com.example.finalexamlibraryvar1kalzhigitovnurbol.repository;


import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Record;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select r from Record r where r.id = ?1")
    @Override
    Optional<Record> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select r from Record r where r.user = ?1")
    Optional<Record> findRecordByUser(User user);


}
