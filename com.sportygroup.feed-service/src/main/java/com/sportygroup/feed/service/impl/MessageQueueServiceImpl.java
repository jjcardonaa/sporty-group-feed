package com.sportygroup.feed.service.impl;

import com.sportygroup.feed.service.MessageQueueService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Mock implementation of the MessageQueueService interface.
 * This service simulates a message queue using a concurrent linked queue.
 */
@Service
@Log4j2
public class MessageQueueServiceImpl implements MessageQueueService {

    private final Queue<String> messageQueue = new ConcurrentLinkedQueue<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessage(String message) {
        messageQueue.offer(message);
        log.info("Mocked message sent {}", message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String consumeMessage() {
       String message = messageQueue.poll();
       log.info(message);

       return message;
    }
}
