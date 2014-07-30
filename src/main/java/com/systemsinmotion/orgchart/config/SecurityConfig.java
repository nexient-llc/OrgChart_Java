package com.systemsinmotion.orgchart.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String USER_QUERY = "select USERNAME,PASSWORD, ENABLED from USERS where USERNAME =?";
	private static final String AUTHORITIES_QUERY = "select u.USERNAME, a.AUTHORITY from USERS u, AUTHORITIES a where u.USERNAME = a.USERNAME and u.USERNAME = ?";

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USER_QUERY).authoritiesByUsernameQuery(AUTHORITIES_QUERY);
		// auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/styles/**", "/img/**", "/scripts/**", "/images/**").permitAll();
		http.authorizeRequests().antMatchers("/admin/*").permitAll();
		http.formLogin().loginPage("/app/admin/login").loginProcessingUrl("/app/j_spring_security_check").usernameParameter("j_username")
		.passwordParameter("j_password");
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

		// http.authorizeRequests().antMatchers("/app", "/", "/app/home").permitAll();
//		http.authorizeRequests().antMatchers("/app/emps").permitAll(); // Differentiates from depts
//		http.authorizeRequests().antMatchers("/app/depts").hasRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/app/admin/login")
//				.permitAll();

		// http.authorizeRequests().antMatchers("/app/jobs").access("hasRole('ROLE_USER') and hasRole('ROLE_ADMIN')").anyRequest().authenticated().and()
		// .formLogin().loginPage("/app/admin/login").permitAll();
		// http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/app/admin/login").permitAll();

	}
}
