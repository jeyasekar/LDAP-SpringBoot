package com.secure.jey.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class LdapConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups")
				.contextSource(context()).passwordCompare().passwordEncoder(new LdapShaPasswordEncoder())
				.passwordAttribute("userpassword");
	}

	/*
	 * Create and initialize an instance which will connect of the LDAP Spring
	 * Security Context Source. It will connect to any of the provided LDAP server
	 * URLs. Parameters: urls A list of string values which are LDAP server URLs. An
	 * example would be ldap://ldap.company.com:389. LDAPS URLs (SSL-secured) may be
	 * used as well, given that Spring Security is able to connect to the server.
	 * Note that these URLs must not include the base DN! baseDn The common Base DN
	 * for all provided servers, e.g. dc=company,dc=com
	 * 
	 */
	@Bean
	public DefaultSpringSecurityContextSource context() {
		return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8181"), "dc=memorynotfound,dc=com");
	}
}
