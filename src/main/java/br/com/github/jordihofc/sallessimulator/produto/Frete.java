package br.com.github.jordihofc.sallessimulator.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Frete {
    private BigDecimal valor;
    private LocalDateTime estimativaDeEntrega;
    private String origem;
    private String destino;


    public Frete(FreteSimulationResponse freteSimulation) {
        this.origem=freteSimulation.getOrigem();
        this.destino=freteSimulation.getDestino();
        this.estimativaDeEntrega=freteSimulation.getEstimativaDeEntrega();
        this.valor=freteSimulation.getValor();
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
