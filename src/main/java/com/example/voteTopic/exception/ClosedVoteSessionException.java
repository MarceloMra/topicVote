package com.example.voteTopic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClosedVoteSessionException extends Exception{

    private Logger logger = LoggerFactory.getLogger(ClosedVoteSessionException.class);
    @Override
    public String getMessage() {
        String errorMessage = "You can not vote to an already closed vote session!";

        logger.error(errorMessage);

        return errorMessage;
    }
}
