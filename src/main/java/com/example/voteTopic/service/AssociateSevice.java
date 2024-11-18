package com.example.voteTopic.service;

import com.example.voteTopic.dto.AssociateDTO;
import com.example.voteTopic.model.Associate;
import com.example.voteTopic.repository.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociateSevice {

    @Autowired
    private AssociateRepository associateRepository;

    public void createAssociate(AssociateDTO associateDTO){
        associateRepository.save(AssociateDTO.toEntity(associateDTO));
    }

    public List<AssociateDTO> findAllAssociates(){
        List<Associate> allAssociates = associateRepository.findAll();
        List<AssociateDTO> associateDTOS = new ArrayList<>();

        allAssociates.forEach(associate -> {
            associateDTOS.add(Associate.toDTO(associate));
        });

        return associateDTOS;
    }
}
