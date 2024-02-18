package br.com.github.jordihofc.sallessimulator.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FreteSimulationResponse {
    private BigDecimal valor;
    private LocalDateTime estimativaDeEntrega;
    private String origem;
    private String destino;

    public FreteSimulationResponse(BigDecimal valor, LocalDateTime estimativaDeEntrega, String origem, String destino) {
        this.valor = valor;
        this.estimativaDeEntrega = estimativaDeEntrega;
        this.origem = origem;
        this.destino = destino;
    }

    public FreteSimulationResponse() {
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEstimativaDeEntrega() {
        return estimativaDeEntrega;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }
}
