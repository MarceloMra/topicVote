package com.example.voteTopic.service;

import com.example.voteTopic.dto.VoteSessionDTO;
import com.example.voteTopic.exception.InvalidEndVoteDateTime;
import com.example.voteTopic.exception.InvalidTopicException;
import com.example.voteTopic.model.Topic;
import com.example.voteTopic.model.VoteSession;
import com.example.voteTopic.repository.VoteSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class VoteSessionService {

    @Autowired
    private VoteSessionRepository voteSessionRepository;

    @Autowired
    private TopicService topicService;

    public void openVoteSession(VoteSessionDTO voteSessionDTO) throws InvalidTopicException, InvalidEndVoteDateTime {
        if(hasValidTopicId(voteSessionDTO)){

            initializeVoteSessionDate(voteSessionDTO);

            VoteSession voteSession = VoteSessionDTO.toEntity(voteSessionDTO);

//            voteSessionDTO.getTopic().setVoteSession(voteSession);

            voteSession.setTopic(topicService.findTopicById(voteSessionDTO.getTopic().getId()).get());
            voteSessionRepository.save(voteSession);
//            topicService.saveOrUpdateTopic(voteSession.getTopic());

        } else {
            throw new InvalidTopicException();
        }
    }

    private void initializeVoteSessionDate(VoteSessionDTO voteSessionDTO) throws InvalidEndVoteDateTime {
        LocalDateTime now = LocalDateTime.now();

        voteSessionDTO.setStartVoteDateTime(now);
        LocalDateTime endVoteDateTime = voteSessionDTO.getEndVoteDateTime();

        if(Objects.isNull(endVoteDateTime)){
            voteSessionDTO.setEndVoteDateTime(now.plusMinutes(1));
        }

        if(Objects.nonNull(endVoteDateTime) && endVoteDateTime.isBefore(now)){
            throw new InvalidEndVoteDateTime();
        }
    }

    private boolean hasValidTopicId(VoteSessionDTO voteSessionDTO){
        long topicId = voteSessionDTO.getTopic().getId();

        return  Objects.nonNull(topicId) &&
                topicService.findTopicById(topicId).isPresent();
    }
}