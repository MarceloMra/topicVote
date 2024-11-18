package com.example.voteTopic.dto;

import com.example.voteTopic.model.Associate;

public class AssociateDTO {
    private long id;
    private String cpf;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public static Associate toEntity(AssociateDTO associateDTO){
        Associate associate = new Associate();

        associate.setId(associateDTO.getId());
        associate.setCpf(associateDTO.getCpf());
        associate.setName(associateDTO.getName());

        return associate;
    }
}
