package cjt.core.config;

import cjt.components.InfoMasksAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
    @Bean
    public InfoMasksAdvice infoMasksAdvice(){ return new InfoMasksAdvice();}
}
