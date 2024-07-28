package com.mikhailkarpov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private ClientRegistrationRepository clientRegistrationRepository;

  @Value("${app.frontend-url}")
  private String frontendUrl;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .cors(cors -> cors.configurationSource(configurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .oauth2Login(login -> login.successHandler(loginSuccessHandler()))
        .logout(logout -> logout.logoutSuccessHandler(logoutSuccessHandler()))
        .build();
  }

  @Bean
  UrlBasedCorsConfigurationSource configurationSource() {
    CorsConfiguration cors = new CorsConfiguration();
    cors.addAllowedOrigin(frontendUrl);
    cors.addAllowedHeader("*");
    cors.addAllowedMethod("*");
    cors.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", cors);
    return source;
  }

  @Bean
  SimpleUrlAuthenticationSuccessHandler loginSuccessHandler() {
    return new SimpleUrlAuthenticationSuccessHandler(frontendUrl);
  }

  @Bean
  OidcClientInitiatedLogoutSuccessHandler logoutSuccessHandler() {
    OidcClientInitiatedLogoutSuccessHandler logoutSuccessHandler =
        new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
    logoutSuccessHandler.setPostLogoutRedirectUri(frontendUrl);
    return logoutSuccessHandler;
  }

}
