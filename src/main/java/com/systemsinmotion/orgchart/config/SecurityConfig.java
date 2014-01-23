package com.systemsinmotion.orgchart.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import com.systemsinmotion.orgchart.web.security.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String AUTHORITIES_QUERY = "authorities.query";

    private static final String USERNAME_QUERY    = "username.query";

    @Autowired
    private DataSource          dataSource;

    @Autowired
    private Environment         environment;

    
    @Override
    public void configure(WebSecurity security) throws Exception {
        security
            .ignoring()
                .antMatchers("/images/*", "/img/*", "/scripts/*", "/styles/*");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeUrls().antMatchers("/").permitAll()
//                .and()
//            .requiresChannel().anyRequest().requiresSecure();

        http
            .formLogin()
                .loginPage("/admin/login")
                .failureUrl("/login")
//                .loginProcessingUrl("/j_spring_security_check")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
                .successHandler(authenticationSuccessHandler());

        http
            .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/logout")
                .invalidateHttpSession(true)
                .logoutUrl("/j_spring_security_logout");

        http
            .exceptionHandling()
                .accessDeniedPage("/403");
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter> decisionVoters = new ArrayList<AccessDecisionVoter>();
        decisionVoters.add(expressionVoter());
        decisionVoters.add(roleVoter());
        decisionVoters.add(authenticatedVoter());

        AccessDecisionManager accessDecisionManager = new AffirmativeBased(decisionVoters);
        return accessDecisionManager;

    }

    @Bean
    public WebExpressionVoter expressionVoter() {
        return new WebExpressionVoter();
    }

    @Bean
    public RoleVoter roleVoter() {
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("");
        return roleVoter;
    }

    @Bean
    public AuthenticatedVoter authenticatedVoter() {
        return new AuthenticatedVoter();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    @Bean
    public StandardPasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Override
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    		.inMemoryAuthentication()
    			.withUser("dude")
    			.password("user")
    			.roles("USER");
    	
//    	auth
//        .jdbcAuthentication()
//            .dataSource(dataSource)
//            .passwordEncoder(passwordEncoder())
//            .usersByUsernameQuery(environment.getProperty(USERNAME_QUERY))
//            .authoritiesByUsernameQuery(environment.getProperty(AUTHORITIES_QUERY));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}