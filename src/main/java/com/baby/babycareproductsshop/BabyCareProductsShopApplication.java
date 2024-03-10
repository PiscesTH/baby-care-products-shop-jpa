package com.baby.babycareproductsshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스케줄러
@EnableJpaAuditing
@SpringBootApplication
@ConfigurationPropertiesScan
//@EnableJpaRepositories(basePackages = {"com.baby.babycareproductsshop"})
public class BabyCareProductsShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabyCareProductsShopApplication.class, args);
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer() {
        return p -> p.setOneIndexedParameters(true);
    }
}
