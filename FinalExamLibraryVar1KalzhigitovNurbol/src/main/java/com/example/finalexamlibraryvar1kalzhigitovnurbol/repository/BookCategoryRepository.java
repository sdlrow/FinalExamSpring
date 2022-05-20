package com.example.finalexamlibraryvar1kalzhigitovnurbol.repository;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    @Transactional
    @Modifying
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("SELECT E FROM #{#entityName} E")
    public List<BookCategory> findAll();

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT e FROM #{#entityName} e WHERE e.title = ?1")
    Optional<Book> findByTitle(String title);


    @Query("select (count(b) > 0) from BookCategory b where b.title = ?1")
    Boolean existsByTitle(String title);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select b from BookCategory b where b.id = ?1")
    @Override
    Optional<BookCategory> findById(Long id);

}
