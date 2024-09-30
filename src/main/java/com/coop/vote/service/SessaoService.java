package com.coop.vote.service;

import com.coop.vote.entity.Pauta;
import com.coop.vote.entity.Sessao;
import com.coop.vote.enums.SessaoStatus;
import com.coop.vote.repository.PautaRepository;
import com.coop.vote.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;

    public SessaoService(SessaoRepository sessaoRepository, PautaRepository pautaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
    }

    public Sessao abrirSessao(Long pautaId, Long duracao) {
        Optional<Pauta> pautaOptional = pautaRepository.findById(pautaId);
        if (pautaOptional.isEmpty()) {
            log.error("Pauta com ID {} não encontrada.", pautaId);
            throw new IllegalArgumentException("Pauta não encontrada.");
        }

        Pauta pauta = pautaOptional.get();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime fimSessao = (duracao != null && duracao > 0) ? now.plusMinutes(duracao) : now.plusMinutes(1);

        Sessao novaSessao = new Sessao();
        novaSessao.setPauta(pauta);
        novaSessao.setTimeStart(now);
        novaSessao.setTimeFinish(fimSessao);
        novaSessao.setStatus(SessaoStatus.ABERTA);

        Sessao sessaoSalva = sessaoRepository.save(novaSessao);
        log.info("Sessão de votação para pauta com ID {} criada com sucesso, com término em: {}", pautaId, fimSessao);

        return sessaoSalva;
    }

    public Sessao fecharSessao(Long sessaoId) {
        Optional<Sessao> sessaoOptional = sessaoRepository.findById(sessaoId);
        if (sessaoOptional.isEmpty()) {
            log.error("Sessão com ID {} não encontrada.", sessaoId);
            throw new IllegalArgumentException("Sessão não encontrada.");
        }

        Sessao sessao = sessaoOptional.get();

        if (sessao.getStatus() == SessaoStatus.FECHADA) {
            log.warn("Sessão com ID {} já está fechada.", sessaoId);
            throw new IllegalStateException("Sessão já está fechada.");
        }

        sessao.setStatus(SessaoStatus.FECHADA);
        Sessao sessaoAtualizada = sessaoRepository.save(sessao);
        log.info("Sessão com ID {} foi fechada com sucesso.", sessaoId);

        return sessaoAtualizada;
    }

    public long contarVotos(Long sessaoId) {
        Optional<Sessao> sessaoOptional = sessaoRepository.findById(sessaoId);
        if (sessaoOptional.isEmpty()) {
            log.error("Sessão com ID {} não encontrada.", sessaoId);
            throw new IllegalArgumentException("Sessão não encontrada.");
        }

        Sessao sessao = sessaoOptional.get();
        long totalVotos = sessao.getVotos().size();
        log.info("Total de votos para a sessão com ID {}: {}", sessaoId, totalVotos);

        return totalVotos;
    }
}
