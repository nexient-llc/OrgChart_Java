package com.systemsinmotion.orgchart.web.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("admin").password("pass").roles("ADMIN");
	  
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, authority from authorities where username=?");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	  http.authorizeRequests()
		.antMatchers("/app/admin").access("hasRole('ROLE_ADMIN')")
		.and()
			.formLogin().loginPage("/app/admin/login").failureUrl("/app/admin/login?error").defaultSuccessUrl("/app/home")
			.loginProcessingUrl("/app/j_spring_security_check")
			.usernameParameter("username").passwordParameter("password")
		.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/app/home")
	  	.and()
	  		.csrf().disable();
 
	}
}