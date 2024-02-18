package br.com.github.jordihofc.sallessimulator.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SolicitacaoCompra(
        @NotNull
        @Size(min = 1)
        List<Long> produtos,
        @NotBlank
        String cepDestino
) {
}
