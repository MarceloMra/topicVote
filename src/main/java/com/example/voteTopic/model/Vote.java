package com.example.voteTopic.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private boolean vote;

    @ManyToOne
    @JoinColumn(name="tb_vote_session_id", nullable=false)
    private VoteSession voteSession;

    @ManyToOne
    @JoinColumn(name="tb_associate_id", nullable=false)
    private Associate associate;

    @Column(nullable = false)
    private LocalDateTime voteDate;

    public long getId() {
        return id;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
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

    public VoteSession getVoteSession() {
        return voteSession;
    }

    public void setVoteSession(VoteSession voteSession) {
        this.voteSession = voteSession;
    }
}
