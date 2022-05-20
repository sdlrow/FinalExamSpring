package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Message;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class MessageServiceTest {
    @Mock
    MessageRepository messageRepository;
    @InjectMocks
    MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        when(messageRepository.findAll()).thenReturn(Arrays.<Message>asList(new Message("destination", "status", Double.valueOf(0), "description", Long.valueOf(1))));

        List<Message> result = messageService.findAll();
        Assertions.assertEquals(Arrays.<Message>asList(new Message("destination", "status", Double.valueOf(0), "description", Long.valueOf(1))), result);
    }

    @Test
    void testFindById() {
        when(messageRepository.findById(anyLong())).thenReturn(null);

        Optional<Message> result = messageService.findById(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindMessageByDestination() {
        when(messageRepository.findMessageByDestination(anyString())).thenReturn(Arrays.<Message>asList(new Message("destination", "status", Double.valueOf(0), "description", Long.valueOf(1))));

        List<Message> result = messageService.findMessageByDestination("destination");
        Assertions.assertEquals(Arrays.<Message>asList(new Message("destination", "status", Double.valueOf(0), "description", Long.valueOf(1))), result);
    }

    @Test
    void testFindMessageByStatusAndDestination() {
        when(messageRepository.findMessageByStatusAndDestination(anyString(), anyString())).thenReturn(Arrays.<Message>asList(new Message("destination", "status", Double.valueOf(0), "description", Long.valueOf(1))));

        List<Message> result = messageService.findMessageByStatusAndDestination("status", "destination");
        Assertions.assertEquals(Arrays.<Message>asList(new Message("destination", "status", Double.valueOf(0), "description", Long.valueOf(1))), result);
    }

    @Test
    void testSave() {
        messageService.save(new Message("destination", "status", Double.valueOf(0), "description", Long.valueOf(1)));
    }

    @Test
    void testDelete() {
        messageService.delete(new Message("destination", "status", Double.valueOf(0), "description", Long.valueOf(1)));
    }

    @Test
    void testFindMessageByAnswerAndDestination() {
        when(messageRepository.findMessageByAnswerAndDestination(anyLong(), anyString())).thenReturn(null);

        Optional<Message> result = messageService.findMessageByAnswerAndDestination(Long.valueOf(1), "destination");
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme