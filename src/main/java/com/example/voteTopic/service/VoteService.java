package com.example.voteTopic.service;

import com.example.voteTopic.dto.VoteDTO;
import com.example.voteTopic.exception.ClosedVoteSessionException;
import com.example.voteTopic.exception.VoteSessionNotPresentException;
import com.example.voteTopic.model.Vote;
import com.example.voteTopic.model.VoteSession;
import com.example.voteTopic.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteSessionService voteSessionService;

    public void createVote(VoteDTO voteDTO) throws ClosedVoteSessionException, VoteSessionNotPresentException {
        voteDTO.setVoteDate(LocalDateTime.now());

        Optional<VoteSession> voteSessionOptional = voteSessionService.findById(voteDTO.getVoteSession().getId());

        if(voteSessionOptional.isPresent()){
            VoteSession voteSession = voteSessionOptional.get();

            if(voteSession.getEndVoteDateTime().isAfter(LocalDateTime.now())){
                voteRepository.save(VoteDTO.toEntity(voteDTO));
            }else{
                throw new ClosedVoteSessionException();
            }
        }else {
            throw new VoteSessionNotPresentException();
        }
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
