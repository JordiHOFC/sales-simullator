package br.com.github.jordihofc.sallessimulator.produto;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class CalculaFreteService {
    private final ProdutoRepository repository;
    private final FreteClient freteClient;
    private final String cepOrigem;

    public CalculaFreteService(
            ProdutoRepository repository,
            FreteClient freteClient,
            @Value("${default.cep.origem}") String cepOrigem
    ) {
        this.repository = repository;
        this.freteClient = freteClient;
        this.cepOrigem = cepOrigem;
    }


    @Transactional(readOnly = true)
    public Frete calcular(SolicitacaoCompra solicitacaoCompra) {
        List<Produto> produtos = repository.findAllById(solicitacaoCompra.produtos());

        var altura = produtos.stream().max(alturaComparator()).get().getAltura();
        var largura = produtos.stream().max(larguraComparator()).get().getLargura();
        var comprimento = produtos.stream().max(comprimentoComparator()).get().getComprimento();
        var valor = produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
        var peso = produtos.stream().map(Produto::getPeso).reduce(BigDecimal.ZERO, BigDecimal::add);

        var freteRequest = new FreteSimulationRequest(
                altura,
                largura,
                comprimento,
                peso,
                cepOrigem,
                solicitacaoCompra.cepDestino(),
                valor
        );
        try {
            var simulacao = freteClient.simulate(freteRequest);
            return new Frete(simulacao);
        } catch (FeignException.FeignClientException.BadRequest ex) {
            throw new InvalidIntegrationException("Entradas nao suportadas");
        }
    }

    private static Comparator<Produto> larguraComparator() {
        return (o1, o2) -> o2.getLargura().compareTo(o1.getLargura());
    }

    private static Comparator<Produto> alturaComparator() {
        return (o1, o2) -> o2.getAltura().compareTo(o1.getAltura());
    }

    private static Comparator<Produto> comprimentoComparator() {
        return (o1, o2) -> o2.getAltura().compareTo(o1.getAltura());
    }


}


