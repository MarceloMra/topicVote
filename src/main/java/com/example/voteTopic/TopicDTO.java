package com.example.voteTopic;

import com.example.voteTopic.model.Topic;
import com.example.voteTopic.model.VoteSession;

public class TopicDTO {
    private long id;
    private String topicDescription;
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

    public static Topic toEntity(TopicDTO topicDTO){
        Topic topic = new Topic();

        topic.setId(topicDTO.getId());
        topic.setTopicDescription(topicDTO.getTopicDescription());
        topic.setVoteSession(topicDTO.getVoteSession());

        return topic;
    }
}
