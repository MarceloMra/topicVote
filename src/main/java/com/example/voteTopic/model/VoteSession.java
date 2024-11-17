package com.example.voteTopic.model;

import com.example.voteTopic.dto.VoteSessionDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_vote_session")
public class VoteSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "start_vote_date_time", nullable = false)
    private LocalDateTime startVoteDateTime;

    @Column(name = "end_vote_date_time", nullable = false)
    private LocalDateTime endVoteDateTime;

    @OneToOne(mappedBy = "voteSession")
    private Topic topic;

    @OneToMany(mappedBy="voteSession")
    private Set<Vote> votes;

    public long getId() {
        return id;
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

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static VoteSessionDTO toDTO(VoteSession voteSession){
        VoteSessionDTO voteSessionDTO = new VoteSessionDTO();

        voteSessionDTO.setId(voteSession.getId());
        voteSessionDTO.setStartVoteDateTime(voteSession.getStartVoteDateTime());
        voteSessionDTO.setEndVoteDateTime(voteSession.getEndVoteDateTime());
        voteSessionDTO.setTopic(voteSession.getTopic());

        return voteSessionDTO;
    }
}
