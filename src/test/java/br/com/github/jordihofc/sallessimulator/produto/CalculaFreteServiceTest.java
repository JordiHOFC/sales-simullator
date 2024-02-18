package br.com.github.jordihofc.sallessimulator.produto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@AutoConfigureWireMock(port = 0)
class CalculaFreteServiceTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:latest")
    );

    @Autowired
    private CalculaFreteService service;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        produtoRepository.deleteAll();
    }


    @Test
    void deveCalcularOFrete() throws JsonProcessingException {
        //cenario
        Produto produto = new Produto(
                "Joystick Xbox One", "Joystick wireless", BigDecimal.TEN,
                new Caracteristica(
                        new BigDecimal("17.7"),
                        new BigDecimal("17.8"),
                        new BigDecimal("7.2"),
                        new BigDecimal("300")
                )
        );

        produtoRepository.save(produto);

        stubFor(
                post(urlPathMatching("/v1/frete"))
                        .willReturn(
                                aResponse()
                                        .withStatus(HttpStatus.CREATED.value())
                                        .withHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(toJson(getSimulacaoDoFrete()))
                        )
        );


        //acao
        Frete frete = service.calcular(new SolicitacaoCompra(List.of(produto.getId()), "32604189"));

        //validacao
        assertEquals(
                new BigDecimal("42.35"), frete.getValor()
        );
        assertNotNull(frete.getEstimativaDeEntrega());
        assertEquals(
                "32605125", frete.getOrigem()
        );
        assertEquals(
                "32604189", frete.getDestino()
        );

    }

    private FreteSimulationResponse getSimulacaoDoFrete() {
        return new FreteSimulationResponse(
                new BigDecimal("42.35"),
                LocalDateTime.of(2024, 02, 18, 00, 00),
                "32605125",
                "32604189"
        );
    }

    private <T> String toJson(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }


}