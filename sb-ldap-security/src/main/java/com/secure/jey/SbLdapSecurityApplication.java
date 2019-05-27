package com.secure.jey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@ComponentScan(basePackages="com.secure.jey")
@SpringBootApplication
public class SbLdapSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbLdapSecurityApplication.class, args);
	}

}
