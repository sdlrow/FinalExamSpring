package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.ERole;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Record;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Role;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.User;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.RecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.*;

class RecordServiceTest {
    @Mock
    RecordRepository recordRepository;
    @InjectMocks
    RecordService recordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        when(recordRepository.findById(anyLong())).thenReturn(null);

        Optional<Record> result = recordService.findById(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindRecordByUser() {
        when(recordRepository.findRecordByUser(any())).thenReturn(null);

        Optional<Record> result = recordService.findRecordByUser(new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testSave() {
        recordService.save(new Record(new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))), "firstName", "secondName", "middleName", LocalDate.of(2022, Month.MAY, 14), "address"));
    }

    @Test
    void testDelete() {
        recordService.delete(new Record(new User(Long.valueOf(1), "username", "email", "password", new HashSet<Role>(Arrays.asList(new Role(Integer.valueOf(0), ERole.ROLE_USER)))), "firstName", "secondName", "middleName", LocalDate.of(2022, Month.MAY, 14), "address"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme