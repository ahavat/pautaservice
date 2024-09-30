package com.coop.vote.dto;

public class VotoRequest {

    private Long sessaoId;
    private Long associadoId;
    private boolean voto;

    public VotoRequest(Long sessaoId, Long associadoId, boolean voto) {
        this.sessaoId = sessaoId;
        this.associadoId = associadoId;
        this.voto = voto;
    }

    public VotoRequest() {
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public Long getAssociadoId() {
        return associadoId;
    }

    public boolean isVoto() {
        return voto;
    }

    @Override
    public String toString() {
        return "VotoRequest{" +
                "sessaoId=" + sessaoId +
                ", associadoId=" + associadoId +
                ", voto=" + voto +
                '}';
    }
}
