package com.example.voteTopic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidEndVoteDateTimeException extends Exception{
    private Logger logger = LoggerFactory.getLogger(InvalidEndVoteDateTimeException.class);
    @Override
    public String getMessage() {
        String errorMessage = "You have provided a endVoteDateTime lower than the start vote date time!";

        logger.error(errorMessage);

        return errorMessage;
    }
}
