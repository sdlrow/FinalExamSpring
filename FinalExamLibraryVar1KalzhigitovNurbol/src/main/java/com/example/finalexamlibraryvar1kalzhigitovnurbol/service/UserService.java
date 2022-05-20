package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Author;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.User;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.AuthorRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("UserService")
public class UserService {
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = false)
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {RuntimeException.class},
            noRollbackFor = {NullPointerException.class},
            rollbackForClassName = {"RuntimeException"},
            noRollbackForClassName = {"NullPointerException"}

    )
    public void save(User user){
        userRepository.save(user);
    }
}
