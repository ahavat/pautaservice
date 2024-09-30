package com.coop.vote.entity;

import com.coop.vote.enums.SessaoStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @Column(nullable = false)
    private LocalDateTime timeStart;

    @Column(nullable = false)
    private LocalDateTime timeFinish;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessaoStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Voto> votos = new ArrayList<>();

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", timeStart=" + timeStart +
                ", timeFinish=" + timeFinish +
                ", status=" + status +
                '}';
    }
}