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
    /**
     * Tiles configurer.
     *
     * @return the tiles configurer
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml" });
        return configurer;
    }

    /**
     * View resolver.
     *
     * @return the tiles view resolver
     */
    @Bean
    public TilesViewResolver viewResolver() {
        return new TilesViewResolver();
    }
}
