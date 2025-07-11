package com.sportygroup.feed.service;

/**
 * MessageQueueService interface defines the contract for sending and consuming messages from a message queue.
 */
public interface MessageQueueService {

    /**
     * Sends a message to the message queue.
     * @param message {@link String} the message to be sent.
     */
    void sendMessage(String message);

    /**
     * Consumes a message from the message queue.
     * @return {@link String} the consumed message, or null if no message is available.
     */
    String consumeMessage();
}
