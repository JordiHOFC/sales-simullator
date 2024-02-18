package br.com.github.jordihofc.sallessimulator.produto;


import br.com.github.jordihofc.sallessimulator.compartilhado.FeignLogginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "freteClient", url = "${services.transportadora.url}", configuration = FeignLogginConfig.class)
public interface FreteClient {
    @PostMapping(value = "/v1/frete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    FreteSimulationResponse simulate(@RequestBody FreteSimulationRequest simulationRequest);
}
