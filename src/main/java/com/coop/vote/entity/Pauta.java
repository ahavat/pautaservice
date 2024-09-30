package com.coop.vote.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @JsonManagedReference
    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sessao> sessoes = new ArrayList<>();

    @Override
    public String toString() {
        return "Pauta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
