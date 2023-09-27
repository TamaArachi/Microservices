package com.microservices.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.glassfish.jersey.internal.util.Property;


@Configuration
@EnableWebSecurity
public class Securityconfig  {
	
	
	@Value("${spring.security.user.name}")
	private String uname;
	
	@Value("${spring.security.user.password}")
	private String pwd;

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder encoder) {
		//inmemory authentication for eureka
		UserDetails user = User.withUsername(uname)
				
				.password(encoder.encode(pwd))
				.roles("USER").build();
		
		return new InMemoryUserDetailsManager(user);
		
	}


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http.csrf().disable()
		.authorizeHttpRequests()
		.anyRequest()
		.authenticated()
		.and().httpBasic()
		.and().build();
 
		
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	


}
