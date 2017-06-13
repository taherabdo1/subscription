package com.mondia.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email ,password, true from user where email=?")
				.authoritiesByUsernameQuery(
						"select user.email , roles.name from user,roles where email= ? and roles.id = user.role_id");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http.httpBasic().and().authorizeRequests().antMatchers("/logout").authenticated()
//				.antMatchers(HttpMethod.POST, "/test").authenticated()// hasRole("MODERATOR")
//				.and().formLogin().successHandler(authenticationSuccessHandler)
//				.failureHandler(new SimpleUrlAuthenticationFailureHandler()).and().logout().and().csrf().disable();

		
		http.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers("/*")
		.permitAll()
		.anyRequest()
		.authenticated()
		.antMatchers(HttpMethod.GET, "/subscribtion/getAll").hasRole("USER")
		.and().formLogin().successHandler(authenticationSuccessHandler)
		.failureHandler(new SimpleUrlAuthenticationFailureHandler())
		.and().logout().and().csrf().disable();
	}

	@Bean
	public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
		return new MySavedRequestAwareAuthenticationSuccessHandler();
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/subscribtion");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		return driverManagerDataSource;
	}
}