package com.example.voteTopic.controller;

import com.example.voteTopic.dto.TopicDTO;
import com.example.voteTopic.dto.VoteSessionDTO;
import com.example.voteTopic.exception.InvalidEndVoteDateTime;
import com.example.voteTopic.exception.InvalidTopicException;
import com.example.voteTopic.service.TopicService;
import com.example.voteTopic.service.VoteSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/v1/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private VoteSessionService voteSessionService;

    private Logger logger = LoggerFactory.getLogger(TopicController.class);

    @PostMapping
    public void createTopic(@RequestBody TopicDTO topic){
        logger.info("Creating topic", topic);

        topicService.createTopic(topic);
    }

    @PostMapping("/open")
    public ResponseEntity<?> openVoteSession(@RequestBody VoteSessionDTO voteSessionDTO) {
        logger.info("Opening vote session to topic");

        try {
            voteSessionService.openVoteSession(voteSessionDTO);
        } catch (InvalidTopicException e) {
            return new ResponseEntity<>(voteSessionDTO, HttpStatus.PRECONDITION_FAILED);
        } catch (InvalidEndVoteDateTime e) {
            return new ResponseEntity<>(voteSessionDTO, HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<>(voteSessionDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllTopics(){
        logger.info("Received find all topics request");

        return new ResponseEntity<>(topicService.findAllTopics(), HttpStatus.OK);
    }
}
