package com.coop.vote.resource;

import com.coop.vote.dto.VotoRequest;
import com.coop.vote.entity.Voto;
import com.coop.vote.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("votos")
public class VotoController {

    private final VotoService votoService;
    private static final Logger logger = LoggerFactory.getLogger(VotoController.class);

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<Object> registrarVoto(@RequestBody VotoRequest votoRequest) {
        try {
            Voto voto = votoService.registrarVoto(votoRequest.getSessaoId(), votoRequest.getAssociadoId(), votoRequest.isVoto());
            if (voto == null) {
                logger.warn("Erro ao registrar voto: associado já votou ou sessão não existe.");
                return ResponseEntity.badRequest().body("Erro ao registrar voto: associado já votou ou sessão não existe.");
            }
            logger.info("Voto registrado com sucesso: {}", voto);
            return ResponseEntity.ok(voto);
        } catch (Exception e) {
            logger.error("Erro ao registrar voto: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar voto: " + e.getMessage());
        }
    }
}
