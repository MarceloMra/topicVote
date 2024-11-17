package com.example.voteTopic.service;

import com.example.voteTopic.dto.TopicDTO;
import com.example.voteTopic.model.Topic;
import com.example.voteTopic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public void createTopic(TopicDTO topicDTO){
        Topic taskToSave = TopicDTO.toEntity(topicDTO);

        topicRepository.save(taskToSave);
    }

    public List<TopicDTO> findAllTopics(){
        List<Topic> allTopics = topicRepository.findAll();
        List<TopicDTO> allTopicDTOs = new ArrayList<>();

        allTopics.forEach(topic -> {
            allTopicDTOs.add(Topic.toDTO(topic));
        });

        return allTopicDTOs;
    }
}
