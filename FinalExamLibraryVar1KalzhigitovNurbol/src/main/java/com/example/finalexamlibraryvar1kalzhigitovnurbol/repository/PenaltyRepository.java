package com.example.finalexamlibraryvar1kalzhigitovnurbol.repository;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Genre;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Penalty;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Record;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.User;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select p from Penalty p where p.user = ?1")
    List<Penalty> getByUser(User user);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select p from Penalty p where p.id = ?1")
    @Override
    Optional<Penalty> findById(Long id);

    @Modifying
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select p from Penalty p")
    public List<Penalty> findAll();
}
