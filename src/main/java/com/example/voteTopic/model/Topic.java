package com.example.voteTopic.model;

import com.example.voteTopic.dto.TopicDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "topic_description", nullable = false)
    private String topicDescription;

    @OneToOne(mappedBy = "topic")
    //@JoinColumn(name = "tb_vote_session_id", referencedColumnName = "id")
    private VoteSession voteSession;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public VoteSession getVoteSession() {
        return voteSession;
    }

    public void setVoteSession(VoteSession voteSession) {
        this.voteSession = voteSession;
    }

    public static TopicDTO toDTO(Topic topic){
        TopicDTO topicDTO = new TopicDTO();

        topicDTO.setId(topic.getId());
        topicDTO.setTopicDescription(topic.getTopicDescription());
        topicDTO.setVoteSession(topic.getVoteSession());

        return topicDTO;
    }
}
