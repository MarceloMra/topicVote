package com.example.voteTopic.controller;

import com.example.voteTopic.dto.VoteDTO;
import com.example.voteTopic.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    private Logger logger = LoggerFactory.getLogger(TopicController.class);

    @PostMapping
    public ResponseEntity<?> registerVote(@RequestBody VoteDTO voteDTO){
        logger.info("Registering vote");
        voteService.createVote(voteDTO);
        return new ResponseEntity<>(voteDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllVotes(){
        logger.info("Request votes");
        return new ResponseEntity<>(voteService.findAllVotes(), HttpStatus.OK);
    }


}
