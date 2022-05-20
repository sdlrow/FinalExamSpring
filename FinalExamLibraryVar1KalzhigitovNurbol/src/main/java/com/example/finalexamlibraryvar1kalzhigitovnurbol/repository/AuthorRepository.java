package com.example.finalexamlibraryvar1kalzhigitovnurbol.repository;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Record;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.DenyAll;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional
    @Modifying
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("SELECT E FROM #{#entityName} E")
    public List<Author> findAll();


    @Query("select (count(a) > 0) from Author a where a.firstName = ?1 and a.lastName = ?2")
    Boolean existsByFirstNameAndLastName(String firstname, String lastname);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select a from Author a where a.id = ?1")
    @Override
    Optional<Author> findById(Long id);

}
