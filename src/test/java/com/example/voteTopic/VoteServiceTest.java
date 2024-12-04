package com.example.voteTopic;

import com.example.voteTopic.dto.VoteDTO;
import com.example.voteTopic.exception.ClosedVoteSessionException;
import com.example.voteTopic.exception.InvalidAssociateException;
import com.example.voteTopic.exception.VoteSessionNotPresentException;
import com.example.voteTopic.model.Associate;
import com.example.voteTopic.model.Topic;
import com.example.voteTopic.model.VoteSession;
import com.example.voteTopic.repository.VoteRepository;
import com.example.voteTopic.service.AssociateSevice;
import com.example.voteTopic.service.VoteService;
import com.example.voteTopic.service.VoteSessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VoteSessionService voteSessionService;

    @Mock
    private AssociateSevice associateSevice;

    @InjectMocks
    private VoteService voteService;

    @Test
    public void registerVoteInAClosedSession(){
        configureClosedVoteSessionService();

        assertThrows(ClosedVoteSessionException.class, () -> {
            voteService.createVote(getVoteDTO());
        });
    }

    @Test
    public void registerVoteWithoutSession(){
        assertThrows(VoteSessionNotPresentException.class, () -> {
            voteService.createVote(getVoteDTOWithoutVoteSession());
        });
    }

    @Test
    public void registerVoteWithoutAssociate(){
        configureOpenVoteSessionService();

        assertThrows(InvalidAssociateException.class, () -> {
            voteService.createVote(getVoteDTOWithoutAssociate());
        });
    }

    private void configureClosedVoteSessionService(){
        Mockito.when(voteSessionService.findById(Mockito.any())).thenReturn(getClosedVoteSession());
    }

    private void configureOpenVoteSessionService(){
        Mockito.when(voteSessionService.findById(Mockito.any())).thenReturn(getOpenVoteSession());
    }

    private Optional<Associate> getAssociate(){
        Associate associate = new Associate();

        associate.setId(1);
        associate.setCpf("123.456.789-19");
        associate.setName("Name Test");

        return Optional.of(associate);
    }

    private Optional<VoteSession> getClosedVoteSession(){
        VoteSession voteSession = new VoteSession();

        voteSession.setId(1);
        voteSession.setTopic(getTopic());
        voteSession.setStartVoteDateTime(LocalDateTime.now().minusMinutes(10));
        voteSession.setEndVoteDateTime(LocalDateTime.now().minusMinutes(8));

        return Optional.of(voteSession);
    }

    private Optional<VoteSession> getOpenVoteSession(){
        VoteSession voteSession = new VoteSession();

        voteSession.setId(1);
        voteSession.setTopic(getTopic());
        voteSession.setStartVoteDateTime(LocalDateTime.now().minusMinutes(1));
        voteSession.setEndVoteDateTime(LocalDateTime.now().plusMinutes(10));

        return Optional.of(voteSession);
    }

    private Topic getTopic(){
        Topic topic = new Topic();

        topic.setId(1);
        topic.setTopicDescription("Test topic");

        return topic;
    }

    private VoteDTO getVoteDTO(){
        VoteDTO voteDTO = new VoteDTO();

        voteDTO.setVote(true);
        voteDTO.setVoteSession(getClosedVoteSession().isPresent() ? getClosedVoteSession().get() : null);

        return voteDTO;
    }

    private VoteDTO getVoteDTOWithoutVoteSession(){
        VoteDTO voteDTO = new VoteDTO();

        voteDTO.setVote(true);

        return voteDTO;
    }

    private VoteDTO getVoteDTOWithoutAssociate(){
        VoteDTO voteDTO = new VoteDTO();

        voteDTO.setVote(true);
        voteDTO.setVoteSession(getOpenVoteSession().get());

        return voteDTO;
    }
}
