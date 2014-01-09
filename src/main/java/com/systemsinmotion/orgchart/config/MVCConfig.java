package com.systemsinmotion.orgchart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.systemsinmotion.orgchart")
public class MVCConfig {
    @Bean
    public TilesViewResolver viewResolver() {
        TilesViewResolver viewResolver = new org.springframework.web.servlet.view.tiles2.TilesViewResolver();
        viewResolver.setViewClass(org.springframework.web.servlet.view.tiles2.TilesView.class);
        return viewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        String[] definitions = {"/WEB-INF/tiles.xml"};
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(definitions);
        return tilesConfigurer;
    }
}
