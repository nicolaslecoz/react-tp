/* (C)2024 */
package fr.jozait.kanban.config;

import fr.jozait.kanban.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Value("${api.base.url}")
  private String apiBaseUrl;

  private final CustomUserDetailService customUserDetailService;

  private final JwtAuthenticationEntryPoint authenticationEntryPoint;

  private final JwtAuthenticationFilter authenticationFilter;

  public SecurityConfig(
      CustomUserDetailService customUserDetailService,
      JwtAuthenticationEntryPoint authenticationEntryPoint,
      JwtAuthenticationFilter authenticationFilter) {
    this.customUserDetailService = customUserDetailService;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.authenticationFilter = authenticationFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((requests) -> {})
        .cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        .authenticationManager(authenticationManager())
        .authorizeHttpRequests(
            requests ->
                requests
                    .requestMatchers("/domain.d.ts")
                    .permitAll()
                    .requestMatchers("/h2-console/**")
                    .permitAll()
                    .requestMatchers(
                        "/swagger-ui.html", "/swagger-ui/**", "/v2/api-docs/**", "/v3/api-docs/**")
                    .permitAll()
                    .requestMatchers("%s/login/**".formatted(apiBaseUrl))
                    .permitAll()
                    .requestMatchers("%s/register/**".formatted(apiBaseUrl))
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .exceptionHandling(
            exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(customUserDetailService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return new ProviderManager(authenticationProvider);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
