package br.com.github.jordihofc.sallessimulator.produto;

import java.math.BigDecimal;

public class FreteSimulationRequest {
    private BigDecimal altura;
    private BigDecimal largura;
    private BigDecimal comprimento;
    private BigDecimal peso;
    private String origem;
    private String destino;
    private BigDecimal valor;

    public FreteSimulationRequest(
            BigDecimal altura,
            BigDecimal largura,
            BigDecimal comprimento,
            BigDecimal peso,
            String cepOrigem,
            String cepDestino,
            BigDecimal valor
    ) {
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.peso = peso;
        this.origem = cepOrigem;
        this.destino = cepDestino;
        this.valor = valor;
    }

    public FreteSimulationRequest() {
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public BigDecimal getLargura() {
        return largura;
    }

    public BigDecimal getComprimento() {
        return comprimento;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
