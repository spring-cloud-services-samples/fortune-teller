package io.spring.cloud.samples.fortuneteller.fortuneservice.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.csrf().disable()
				.authorizeRequests()
				.requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
				.antMatchers("/**").permitAll()
				.and()
				.httpBasic();
		// @formatter:on
	}
}
