package com.example.voteTopic.model;

import com.example.voteTopic.dto.AssociateDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_associate")
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String name;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static AssociateDTO toDTO(Associate associate){
        AssociateDTO associateDTO = new AssociateDTO();

        associateDTO.setId(associate.getId());
        associateDTO.setCpf(associate.getCpf());
        associateDTO.setName(associate.getName());

        return associateDTO;
    }
}
