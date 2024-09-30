package com.coop.vote.resource;

import com.coop.vote.entity.Associado;
import com.coop.vote.service.AssociadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("associados")
public class AssociadoController {

    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping
    public ResponseEntity<Associado> criarAssociado(@RequestBody Associado associado) {
        Associado novoAssociado = associadoService.criarAssociado(associado);
        return ResponseEntity.ok(novoAssociado);
    }
}
