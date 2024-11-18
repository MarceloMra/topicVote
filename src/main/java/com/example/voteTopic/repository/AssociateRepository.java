package com.example.voteTopic.repository;

import com.example.voteTopic.model.Associate;
import com.example.voteTopic.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

}