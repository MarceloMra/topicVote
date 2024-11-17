package com.example.voteTopic.repository;

import com.example.voteTopic.model.VoteSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {

}