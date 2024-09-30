package com.coop.vote.service;

import com.coop.vote.entity.Sessao;
import com.coop.vote.entity.Voto;
import com.coop.vote.repository.SessaoRepository;
import com.coop.vote.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final SessaoRepository sessaoRepository;
    private static final Logger logger = LoggerFactory.getLogger(VotoService.class);

    public VotoService(VotoRepository votoRepository, SessaoRepository sessaoRepository) {
        this.votoRepository = votoRepository;
        this.sessaoRepository = sessaoRepository;
    }

    public Voto registrarVoto(Long sessaoId, Long associadoId, boolean yesOrNo) {
        Optional<Sessao> sessaoOpt = sessaoRepository.findById(sessaoId);
        if (sessaoOpt.isEmpty()) {
            logger.error("Sessão com ID {} não encontrada", sessaoId);
            throw new IllegalArgumentException("Sessão não encontrada.");
        }

        Sessao sessao = sessaoOpt.get();

        if (votoRepository.existsByAssociadoIdAndSessaoId(associadoId, sessaoId)) {
            logger.error("Associado com ID {} já votou na sessão {}", associadoId, sessaoId);
            throw new IllegalStateException("Associado já votou na sessão.");
        }

        try {
            Voto voto = new Voto();
            voto.setSessao(sessao);
            voto.setAssociadoId(associadoId);
            voto.setYes(yesOrNo);

            Voto savedVoto = votoRepository.save(voto);
            logger.info("Voto registrado com sucesso: {}", savedVoto);

            return savedVoto;

        } catch (Exception e) {
            logger.error("Erro ao registrar o voto: {}", e.getMessage());
            throw new RuntimeException("Erro ao registrar o voto.");
        }
    }

}
