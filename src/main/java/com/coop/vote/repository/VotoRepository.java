package com.coop.vote.repository;

import com.coop.vote.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByAssociadoIdAndSessaoId(Long associadoId, Long sessaoId);
    List<Voto> findBySessaoPautaId(Long pautaId);
}
