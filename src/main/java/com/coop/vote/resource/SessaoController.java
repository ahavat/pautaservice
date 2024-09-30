package com.coop.vote.resource;

import com.coop.vote.entity.Sessao;
import com.coop.vote.service.SessaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sessoes")
public class SessaoController {

    private static final Logger logger = LoggerFactory.getLogger(SessaoController.class);
    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @GetMapping("contabilizar")
    public ResponseEntity<?> contabilizarVotos(@RequestParam Long sessaoId) {
        try {
            long totalVotos = sessaoService.contarVotos(sessaoId);
            return ResponseEntity.ok(totalVotos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Sessão não encontrada.");
        }
    }

    @PostMapping("fechar")
    public ResponseEntity<?> fecharSessao(@RequestParam Long sessaoId) {
        try {
            Sessao sessaoFechada = sessaoService.fecharSessao(sessaoId);
            return ResponseEntity.ok(sessaoFechada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Sessão não encontrada.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("abrir")
    public ResponseEntity<Object> abrirSessao(@RequestParam Long pautaId, @RequestParam Long duracao) {
        if (pautaId == null) {
            logger.error("Erro ao abrir sessão: Pauta ID não pode ser null");
            return ResponseEntity.badRequest().body("Pauta ID não pode ser nulo");
        }
        try {
            Sessao novaSessao = sessaoService.abrirSessao(pautaId, duracao);
            logger.info("Sessão aberta com sucesso: {}", novaSessao);
            return ResponseEntity.ok(novaSessao);
        } catch (Exception e) {
            logger.error("Erro ao abrir sessão: {}", e.getMessage());
            return ResponseEntity.status(500).body("Erro ao abrir sessão");
        }
    }
}
