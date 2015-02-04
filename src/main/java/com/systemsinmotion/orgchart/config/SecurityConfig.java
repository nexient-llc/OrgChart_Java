package com.systemsinmotion.orgchart.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/app/admin/")
				.access("hasRole('ROLE_ADMIN')").and().formLogin()
				.loginPage("/app/login")
				.usernameParameter("username").passwordParameter("password")
				.failureUrl("/app/login?error").and().logout().logoutUrl("/app/logout")
				.logoutSuccessUrl("/app/home").and().csrf();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select username, password, enabled from users where username=?")
				.authoritiesByUsernameQuery(
						"select username, authority from authorities where username=?");
	}

	@Override
	public void configure(WebSecurity security) throws Exception {
		// @formatter:off
		security.ignoring().antMatchers("/images/*", "/img/*", "/scripts/*",
				"/styles/*");
		// @formatter:on
	}
}