package com.example.finalexamlibraryvar1kalzhigitovnurbol.repository;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Genre;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select m from Message m")
    public List<Message> findAll();

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select m from Message m where m.id = ?1")
    @Override
    Optional<Message> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select m from Message m where m.destination = ?1")
    List<Message> findMessageByDestination(String destination);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select m from Message m where m.status = ?1 and m.destination = ?2")
    List<Message> findMessageByStatusAndDestination(String status, String destination);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select m from Message m where m.answer = ?1 and m.destination = ?2")
    Optional<Message> findMessageByAnswerAndDestination(Long answer, String destination);
}
