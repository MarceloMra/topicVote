package com.example.voteTopic.controller;

import com.example.voteTopic.dto.AssociateDTO;
import com.example.voteTopic.dto.VoteDTO;
import com.example.voteTopic.service.AssociateSevice;
import com.example.voteTopic.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/associate")
public class AssociateController {
    private Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private AssociateSevice associateSevice;

    @PostMapping
    public ResponseEntity<?> createAssociate(@RequestBody AssociateDTO associateDTO){
        logger.info("Registering associate");
        associateSevice.createAssociate(associateDTO);
        return new ResponseEntity<>(associateDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllAssociates(){
        logger.info("Request associates");
        return new ResponseEntity<>(associateSevice.findAllAssociates(), HttpStatus.OK);
    }


}
