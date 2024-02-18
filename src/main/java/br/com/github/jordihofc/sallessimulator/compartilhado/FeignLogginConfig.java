package br.com.github.jordihofc.sallessimulator.compartilhado;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignLogginConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
