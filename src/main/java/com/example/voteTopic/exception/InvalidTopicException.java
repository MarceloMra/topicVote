package com.example.voteTopic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidTopicException extends Exception{

    private Logger logger = LoggerFactory.getLogger(InvalidTopicException.class);

    @Override
    public String getMessage() {
        String errorMessage = "You have provided and invalid null or an non-existent topic id!";

        logger.error(errorMessage);

        return errorMessage;
    }
}
