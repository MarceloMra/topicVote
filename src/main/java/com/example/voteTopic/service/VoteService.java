package com.example.voteTopic.service;

import com.example.voteTopic.dto.VoteDTO;
import com.example.voteTopic.model.Vote;
import com.example.voteTopic.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public void createVote(VoteDTO voteDTO){
        voteDTO.setVoteDate(LocalDateTime.now());
        voteRepository.save(VoteDTO.toEntity(voteDTO));
    }

    public List<VoteDTO> findAllVotes(){
        List<Vote> allVotes = voteRepository.findAll();
        List<VoteDTO> votesDTOs = new ArrayList<>();

        allVotes.forEach(vote -> {
            votesDTOs.add(Vote.toDTO(vote));
        });

        return votesDTOs;
    }
}
