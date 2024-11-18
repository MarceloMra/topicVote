package com.example.voteTopic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoteSessionNotPresentException extends Exception{
    private Logger logger = LoggerFactory.getLogger(VoteSessionNotPresentException.class);
    @Override
    public String getMessage() {
        String errorMessage = "You must provide the vote session to vote!";

        logger.error(errorMessage);

        return errorMessage;
    }
}
