package com.example.voteTopic.service;

import com.example.voteTopic.dto.VoteSessionDTO;
import com.example.voteTopic.exception.InvalidEndVoteDateTimeException;
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


    public Optional<VoteSession> findById(Long id){
        return voteSessionRepository.findById(id);
    }

    public void openVoteSession(VoteSessionDTO voteSessionDTO) throws InvalidTopicException, InvalidEndVoteDateTimeException {
        if(hasValidTopicId(voteSessionDTO)){

            initializeVoteSessionDate(voteSessionDTO);

            VoteSession voteSession = VoteSessionDTO.toEntity(voteSessionDTO);

            voteSession.setTopic(topicService.findTopicById(voteSessionDTO.getTopic().getId()).get());
            voteSessionRepository.save(voteSession);

            configureTimerToNotify(voteSessionDTO.getStartVoteDateTime(), voteSessionDTO.getEndVoteDateTime(), voteSession);
        } else {
            throw new InvalidTopicException();
        }
    }

    private void initializeVoteSessionDate(VoteSessionDTO voteSessionDTO) throws InvalidEndVoteDateTimeException {
        LocalDateTime now = LocalDateTime.now();

        voteSessionDTO.setStartVoteDateTime(now);
        LocalDateTime endVoteDateTime = voteSessionDTO.getEndVoteDateTime();

        if(Objects.isNull(endVoteDateTime)){
            voteSessionDTO.setEndVoteDateTime(now.plusMinutes(1));
        }

        if(Objects.nonNull(endVoteDateTime) && endVoteDateTime.isBefore(now)){
            throw new InvalidEndVoteDateTimeException();
        }

    }

    private void configureTimerToNotify(LocalDateTime now, LocalDateTime endVoteDateTime, final VoteSession voteSession) {
        Duration waitTime = Duration.between(now, endVoteDateTime);
        TimerTask task = new TimerTask() {
            public void run() {
                int votesCount = 0;

                try {
                    Optional<VoteSession> voteSessionSearch = voteSessionRepository.findById(voteSession.getId());

                    if(voteSessionSearch.isPresent()){
                        votesCount = voteSessionSearch.get().getVotes().size();
                    }
                }catch (Exception e){
                    logger.error(e.getMessage());
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
