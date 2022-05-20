package com.example.finalexamlibraryvar1kalzhigitovnurbol.repository;



import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT e FROM #{#entityName} e WHERE e.username = ?1")
    Optional<User> findByUsername(String username);

    @Query("select (count(u) > 0) from User u where u.username = ?1")
    Boolean existsByUsername(String username);


    @Query("select (count(u) > 0) from User u where u.email = ?1")
    Boolean existsByEmail(String email);
}