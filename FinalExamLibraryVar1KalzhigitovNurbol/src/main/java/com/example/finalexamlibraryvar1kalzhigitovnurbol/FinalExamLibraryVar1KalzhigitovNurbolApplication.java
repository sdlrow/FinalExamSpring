package com.example.finalexamlibraryvar1kalzhigitovnurbol;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.response.MessageResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableJms
public class FinalExamLibraryVar1KalzhigitovNurbolApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalExamLibraryVar1KalzhigitovNurbolApplication.class, args);
//        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-config.xml");
//        MessageResponse obj = (MessageResponse) context.getBean("messageResponse");
//        obj.getMessage();
//        context.registerShutdownHook();

    }

}
