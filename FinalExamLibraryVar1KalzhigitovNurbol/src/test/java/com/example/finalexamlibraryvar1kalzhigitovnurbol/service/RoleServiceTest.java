package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.ERole;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Role;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class RoleServiceTest {
    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByName() {
        when(roleRepository.findByName(any())).thenReturn(null);

        Optional<Role> result = roleService.findByName(ERole.ROLE_USER);
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme