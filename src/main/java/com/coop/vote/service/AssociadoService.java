package com.coop.vote.service;

import com.coop.vote.entity.Associado;
import com.coop.vote.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    public Associado criarAssociado(Associado associado) {
        return associadoRepository.save(associado);
    }
}
