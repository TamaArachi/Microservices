package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity)
	{
        //remove authentication for /eureka/** path only
        
        serverHttpSecurity.csrf(csrf -> csrf.disable())
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/eureka/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated())
                //enable resource server and json web token (jwt) in apigateway 
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
		
		return serverHttpSecurity.build();
								  
								  
								  
								  
	}

}
