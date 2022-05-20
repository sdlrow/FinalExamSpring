package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.ERole;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Penalty;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Role;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.User;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.PenaltyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class PenaltyServiceTest {
    @Mock
    PenaltyRepository penaltyRepository;
    @InjectMocks
    PenaltyService penaltyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetByUser() {
        when(penaltyRepository.getByUser(any())).thenReturn(Arrays.<Penalty>asList(new Penalty("name", "description", new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))))));

        List<Penalty> result = penaltyService.getByUser(new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))));
        Assertions.assertEquals(Arrays.<Penalty>asList(new Penalty("name", "description", new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))))), result);
    }

    @Test
    void testFindById() {
        when(penaltyRepository.findById(anyLong())).thenReturn(null);

        Optional<Penalty> result = penaltyService.findById(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindAll() {
        when(penaltyRepository.findAll()).thenReturn(Arrays.<Penalty>asList(new Penalty("name", "description", new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))))));

        List<Penalty> result = penaltyService.findAll();
        Assertions.assertEquals(Arrays.<Penalty>asList(new Penalty("name", "description", new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))))), result);
    }

    @Test
    void testSave() {
        penaltyService.save(new Penalty("name", "description", new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER))))));
    }

    @Test
    void testDelete() {
        penaltyService.delete(new Penalty("name", "description", new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER))))));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme