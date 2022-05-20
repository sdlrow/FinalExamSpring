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
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT e FROM #{#entityName} e WHERE e.title = ?1")
    Optional<Book> findByTitle(String title);

    @Transactional
    @Modifying
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("SELECT E FROM #{#entityName} E")
    public List<Book> findAll();

    @Query("select (count(b) > 0) from Book b where b.title = ?1")
    Boolean existsByTitle(String title);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select b from Book b where b.bookCategory = ?1")
    Optional<Book> findByBookCategory(BookCategory bookCategory);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select b from Book b where b.id = ?1")
    @Override
    Optional<Book> findById(Long id);
}