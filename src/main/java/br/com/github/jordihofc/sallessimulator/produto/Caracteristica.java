package br.com.github.jordihofc.sallessimulator.produto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record Caracteristica(
        @Column(nullable = false) BigDecimal altura,
        @Column(nullable = false)  BigDecimal largura,
        @Column(nullable = false) BigDecimal comprimento,
        @Column(nullable = false) BigDecimal peso
){}