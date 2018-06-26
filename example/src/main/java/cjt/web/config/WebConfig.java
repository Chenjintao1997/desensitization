package cjt.web.config;

import cjt.components.InfoMasksAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public InfoMasksAdvice infoMasksAdvice(){ return new InfoMasksAdvice();}
}
