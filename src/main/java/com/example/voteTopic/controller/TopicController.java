package com.example.voteTopic.controller;

import com.example.voteTopic.dto.TopicDTO;
import com.example.voteTopic.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/v1/Topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    private Logger logger = LoggerFactory.getLogger(TopicController.class);

    @PostMapping
    public void createTopic(@RequestBody TopicDTO topic){
        logger.info("Received topic create request ", topic);

        topicService.createTopic(topic);

        logger.info("Finish topic create request ", topic);
    }

    @GetMapping
    public ResponseEntity<?> findAllTopics(){
        logger.info("Received find all topics request");

        return new ResponseEntity<>(topicService.findAllTopics(), HttpStatus.OK);
    }
}
