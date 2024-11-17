package com.example.voteTopic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_associate")
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long cpf;

    @Column(nullable = false)
    private String name;

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
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
}
