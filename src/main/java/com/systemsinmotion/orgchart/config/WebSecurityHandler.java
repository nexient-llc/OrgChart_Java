package com.systemsinmotion.orgchart.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityHandler extends WebSecurityConfigurerAdapter {

	private static final String AUTHORITIES_QUERY = "username.query";
	private static final String USERNAME_QUERY = "authorities.query";
	@Autowired
	private DataSource dataSource;
	@Autowired
	private Environment env;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// formatter: off

		http.authorizeRequests().antMatchers("/").permitAll();

		http.formLogin().loginPage("/admin")
				.loginProcessingUrl("/j_spring_security_check")
				.usernameParameter("j_username")
				.passwordParameter("j_password");

		http.csrf().disable();

		// formatter: on
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// formatter:off
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(env.getProperty(USERNAME_QUERY))
				.authoritiesByUsernameQuery(env.getProperty(AUTHORITIES_QUERY));
		// formatter:on

	}

	@Override
	public void configure(WebSecurity security) throws Exception {
		// @formatter:off
		security.ignoring().antMatchers("/images/*", "/img/*", "/scripts/*",
				"/styles/*");
		// @formatter:on
	}
}
