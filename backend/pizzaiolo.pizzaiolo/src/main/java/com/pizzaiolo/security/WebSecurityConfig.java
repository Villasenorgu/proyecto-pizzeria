package com.pizzaiolo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:4200");
		config.setAllowCredentials(false);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Value("${app.jwt.secret.key}")
	private String SECRET;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()			
			.addFilterAfter(new JWTAuthorizationFilter(SECRET), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(
                    "/v3/api-docs",
                    "/v2/api-docs",
                    "/swagger-resources/**",
                    "/swagger-ui/**",
                    "/webjars/**",                   
                    "/api/v1/favicon.ico"
            ).permitAll()
			//hasAnyRole("USER", "SUPER_ADMIN", "ADMIN", "DELIVERY")
			.antMatchers(HttpMethod.GET, "/api/v1/pizzas/**").permitAll()
			.antMatchers("/api/v1/pizzas/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
			.antMatchers("/api/v1/pedidos/elaborandose", "/api/v1/pedidos/solicitado").hasAnyRole("SUPER_ADMIN", "ADMIN", "DELIVERY")
			//.antMatchers("/api/v1/pedidos/elaborandose").hasAnyRole("SUPER_ADMIN", "ADMIN")
			.anyRequest().denyAll();
			//.anyRequest().permitAll();
	}
}
