package com.example.finalexamlibraryvar1kalzhigitovnurbol.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@PropertySource("classpath:application.properties")
@EnableAsync
public class scheduleManager {
    private static final Logger log = LoggerFactory.getLogger(scheduleManager.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Async
    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        log.info("reportFixedRate:" + dateFormat.format(new Date()));
    }

    @Async
    @Scheduled(fixedDelay = 10000)
    public void fixedDelay() {
        log.info("reportFixedDelay:" + dateFormat.format(new Date()));
    }

    @Scheduled(initialDelay = 2500, fixedDelay = 2500)
    public void initialDelay() {
        log.info("reportInitialDelay:" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "${cron.expression}")
    public void inCronExpressions() {
        log.info("reportInCronExpressions:" + dateFormat.format(new Date()));
    }


}