package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.ERole;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Role;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.User;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        Optional<User> result = userService.findByUsername("username");
        Assertions.assertEquals(null, result);
    }

    @Test
    void testExistsByUsername() {
        when(userRepository.existsByUsername(anyString())).thenReturn(Boolean.TRUE);

        Boolean result = userService.existsByUsername("username");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testExistsByEmail() {
        when(userRepository.existsByEmail(anyString())).thenReturn(Boolean.TRUE);

        Boolean result = userService.existsByEmail("email");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testSave() {
        userService.save(new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme