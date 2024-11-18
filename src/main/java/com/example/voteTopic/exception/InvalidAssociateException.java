package com.example.voteTopic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidAssociateException extends Exception{
    private Logger logger = LoggerFactory.getLogger(InvalidAssociateException.class);
    @Override
    public String getMessage() {
        String errorMessage = "You must provide a valid associate to vote!";

        logger.error(errorMessage);

        return errorMessage;
    }
}
