package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Book;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.BookCategory;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Message;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.BookRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("MessageService")
public class MessageService {
    private MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Optional<Message> findById(Long id){
        return messageRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Message> findMessageByDestination(String destination){
        return messageRepository.findMessageByDestination(destination);
    }
    @Transactional(readOnly = true)
    public List<Message> findMessageByStatusAndDestination(String status, String destination){
        return messageRepository.findMessageByStatusAndDestination(status, destination);
    }
    @Transactional(isolation = Isolation.READ_UNCOMMITTED,
            rollbackFor = {RuntimeException.class},
            noRollbackFor = {NullPointerException.class},
            rollbackForClassName = {"RuntimeException"},
            noRollbackForClassName = {"NullPointerException"}

    )
    public void save(Message message){
        messageRepository.save(message);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = {RuntimeException.class})
    public void delete(Message message){
        messageRepository.delete(message);
    }

    @Transactional(readOnly = false)
    public Optional<Message> findMessageByAnswerAndDestination(Long answer, String destination){
        return messageRepository.findMessageByAnswerAndDestination(answer, destination);
    }
}
