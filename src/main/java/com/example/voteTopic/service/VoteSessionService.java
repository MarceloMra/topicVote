package com.example.voteTopic.service;

import com.example.voteTopic.dto.VoteSessionDTO;
import com.example.voteTopic.exception.InvalidEndVoteDateTime;
import com.example.voteTopic.exception.InvalidTopicException;
import com.example.voteTopic.model.VoteSession;
import com.example.voteTopic.repository.VoteSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class VoteSessionService {

    @Autowired
    private VoteSessionRepository voteSessionRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    private Logger logger = LoggerFactory.getLogger(VoteSessionService.class);

    public void openVoteSession(VoteSessionDTO voteSessionDTO) throws InvalidTopicException, InvalidEndVoteDateTime {
        if(hasValidTopicId(voteSessionDTO)){

            VoteSession voteSession = VoteSessionDTO.toEntity(voteSessionDTO);

            initializeVoteSessionDate(voteSessionDTO, voteSession);

            voteSession.setTopic(topicService.findTopicById(voteSessionDTO.getTopic().getId()).get());
            voteSessionRepository.save(voteSession);

        } else {
            throw new InvalidTopicException();
        }
    }

    private void initializeVoteSessionDate(VoteSessionDTO voteSessionDTO, VoteSession voteSession) throws InvalidEndVoteDateTime {
        LocalDateTime now = LocalDateTime.now();

        voteSessionDTO.setStartVoteDateTime(now);
        LocalDateTime endVoteDateTime = voteSessionDTO.getEndVoteDateTime();

        if(Objects.isNull(endVoteDateTime)){
            voteSessionDTO.setEndVoteDateTime(now.plusMinutes(1));
        }

        if(Objects.nonNull(endVoteDateTime) && endVoteDateTime.isBefore(now)){
            throw new InvalidEndVoteDateTime();
        }

        configureTimerToNotify(now, voteSessionDTO.getEndVoteDateTime(), voteSession);
    }

    private void configureTimerToNotify(LocalDateTime now, LocalDateTime endVoteDateTime, final VoteSession voteSession) {
        Duration waitTime = Duration.between(now, endVoteDateTime);
        TimerTask task = new TimerTask() {
            public void run() {
                int votesCount = 0;
                Optional<VoteSession> voteSessionSearch = voteSessionRepository.findById(voteSession.getId());

                if(voteSessionSearch.isPresent()){
                    votesCount = voteSessionSearch.get().getVotes().size();
                }

                rabbitMQProducer.sendMessage("Votes: "+votesCount);
            }
        };
        Timer timer = new Timer("Timer");

        timer.schedule(task, waitTime.toMillis());
    }

    private boolean hasValidTopicId(VoteSessionDTO voteSessionDTO){
        long topicId = voteSessionDTO.getTopic().getId();

        return  Objects.nonNull(topicId) &&
                topicService.findTopicById(topicId).isPresent();
    }
}
