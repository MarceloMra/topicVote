package com.example.voteTopic.dto;

import com.example.voteTopic.model.Associate;
import com.example.voteTopic.model.Vote;
import com.example.voteTopic.model.VoteSession;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class VoteDTO {
    private long id;
    private boolean vote;
    private VoteSession voteSession;
    private Associate associate;
    private LocalDateTime voteDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public VoteSession getVoteSession() {
        return voteSession;
    }

    public void setVoteSession(VoteSession voteSession) {
        this.voteSession = voteSession;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public LocalDateTime getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDateTime voteDate) {
        this.voteDate = voteDate;
    }

    public static Vote toEntity(VoteDTO voteDTO){
        Vote vote = new Vote();

        vote.setVote(voteDTO.isVote());
        vote.setVoteDate(voteDTO.getVoteDate());
        vote.setVoteSession(voteDTO.getVoteSession());
        vote.setAssociate(voteDTO.getAssociate());

        return vote;
    }
}
