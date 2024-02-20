package com.itsolution.backend.config; // ou o pacote adequado onde você deseja colocar a classe

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permite CORS em todas as URLs
            .allowedOrigins("*") // permitir todas as origens (você pode restringir para apenas as necessárias)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // métodos permitidos
            .allowedHeaders("*"); // permitir todos os cabeçalhos
    }
}