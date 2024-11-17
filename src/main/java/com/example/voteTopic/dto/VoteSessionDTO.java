package com.example.voteTopic.dto;

import com.example.voteTopic.model.Topic;
import com.example.voteTopic.model.VoteSession;

import java.time.LocalDateTime;

public class VoteSessionDTO {
    private long id;
    private LocalDateTime startVoteDateTime;
    private LocalDateTime endVoteDateTime;
    private Topic topic;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartVoteDateTime() {
        return startVoteDateTime;
    }

    public void setStartVoteDateTime(LocalDateTime startVoteDateTime) {
        this.startVoteDateTime = startVoteDateTime;
    }

    public LocalDateTime getEndVoteDateTime() {
        return endVoteDateTime;
    }

    public void setEndVoteDateTime(LocalDateTime endVoteDateTime) {
        this.endVoteDateTime = endVoteDateTime;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public static VoteSession toEntity(VoteSessionDTO voteSessionDTO){
        VoteSession voteSession = new VoteSession();

        voteSession.setId(voteSessionDTO.getId());
        voteSession.setStartVoteDateTime(voteSessionDTO.getStartVoteDateTime());
        voteSession.setEndVoteDateTime(voteSessionDTO.getEndVoteDateTime());
        voteSession.setTopic(voteSessionDTO.getTopic());

        return voteSession;
    }
}
