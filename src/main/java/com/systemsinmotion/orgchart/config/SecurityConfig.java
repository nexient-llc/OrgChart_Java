package com.systemsinmotion.orgchart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/styles/*", "/img/*", "/scripts/*", "/images/*").permitAll();
		http.authorizeRequests().antMatchers("/app", "/").permitAll();
		http.authorizeRequests().antMatchers("/app/*").permitAll(); // This permits all pages, just for working on
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/app/admin/login").permitAll();
	}
}
