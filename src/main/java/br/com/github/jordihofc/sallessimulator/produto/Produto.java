package br.com.github.jordihofc.sallessimulator.produto;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal preco;
    @Embedded
    private Caracteristica caracteristicas;

    public Produto(String name, String descricao, BigDecimal preco, Caracteristica caracteristicas) {
        this.name = name;
        this.descricao = descricao;
        this.preco = preco;
        this.caracteristicas = caracteristicas;
    }

    /**
     * Este construtor é uma exigencia do Hibernate
     */
    @Deprecated
    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getPeso() {
        return caracteristicas.peso();
    }

    public BigDecimal getAltura() {
        return caracteristicas.altura();
    }

    public BigDecimal getLargura() {
        return caracteristicas.largura();
    }

    public BigDecimal getComprimento() {
        return caracteristicas.comprimento();
    }


}
