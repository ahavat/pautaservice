package com.coop.vote.resource;

import com.coop.vote.entity.Pauta;
import com.coop.vote.service.PautaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    private static final Logger logger = LoggerFactory.getLogger(PautaController.class);

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarPauta(@RequestBody Pauta pauta) {
        if (pauta.getTitulo().isEmpty() || pauta.getDescricao().isEmpty()) {
            logger.error("Erro ao cadastrar pauta: título e descrição vazios");
            return ResponseEntity.badRequest().body("Título e descrição não podem ser vazios");
        }
        try{
            Pauta savedPauta = pautaService.cadastrarPauta(pauta);
            logger.info("Pauta cadastrada com sucesso: {}", savedPauta);
            return ResponseEntity.ok(savedPauta);

        } catch (Exception e) {
            logger.error("Erro ao cadastrar pauta:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar pauta");
        }
    }

    @GetMapping
    public List<Pauta> listarPautas() {
        return pautaService.listarPautas();
    }
}
