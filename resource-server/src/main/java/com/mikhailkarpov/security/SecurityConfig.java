package com.mikhailkarpov.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .cors(cors -> cors.configurationSource(configurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .oauth2Login(login -> login.successHandler(loginSuccessHandler()))
        .build();
  }

  @Bean
  UrlBasedCorsConfigurationSource configurationSource() {
    CorsConfiguration cors = new CorsConfiguration();
    cors.addAllowedOrigin("http://localhost:5173");
    cors.addAllowedHeader("*");
    cors.addAllowedMethod("*");
    cors.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", cors);
    return source;
  }

  @Bean
  SimpleUrlAuthenticationSuccessHandler loginSuccessHandler() {
    return new SimpleUrlAuthenticationSuccessHandler("http://localhost:5173");
  }

}
