package com.coop.vote.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long associadoId;

    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    @JsonBackReference
    private Sessao sessao;

    @Column(nullable = false)
    private boolean yes;


    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", associadoId=" + associadoId +
                ", sessao=" + sessao +
                ", yes=" + yes +
                '}';
    }
}
