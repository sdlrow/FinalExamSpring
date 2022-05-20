package com.example.finalexamlibraryvar1kalzhigitovnurbol.jms;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.Message;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.request.MessageRequest;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
public class MessageReciever {

    MessageService messageService;

    @Autowired
    public MessageReciever(MessageService messageService) {
        this.messageService = messageService;
    }

    @JmsListener(destination = "PaymentReview", containerFactory = "myFactory")
    public void receiveMessage(Message transaction) {
        messageService.save(transaction);
    }

    @JmsListener(destination = "VerifyReview", containerFactory = "myFactory")
    public void receiveMessageAdmin(Message transaction) {
        messageService.save(transaction);
    }

}
