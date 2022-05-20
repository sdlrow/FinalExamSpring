package com.example.finalexamlibraryvar1kalzhigitovnurbol.repository;


import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.ERole;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT e FROM #{#entityName} e WHERE e.name = ?1")
    Optional<Role> findByName(ERole name);
}