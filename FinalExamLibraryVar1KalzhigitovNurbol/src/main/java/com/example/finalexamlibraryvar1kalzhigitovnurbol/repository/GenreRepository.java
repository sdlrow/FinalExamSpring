package com.example.finalexamlibraryvar1kalzhigitovnurbol.repository;


import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Genre;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.annotation.security.DenyAll;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Modifying
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("SELECT E FROM #{#entityName} E")
    public List<Genre> findAll();

    @Query("select (count(g) > 0) from Genre g where g.title = ?1")
    Boolean existsByTitle(String title);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select g from Genre g where g.id = ?1")
    @Override
    Optional<Genre> findById(Long id);
}