package com.bookstore.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final String allowedOrigins;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
                                  @Value("${app.allowed-origins}") String allowedOrigins) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.allowedOrigins = allowedOrigins;
    }

    // Main security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	 http.csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
             // ✅ Allow Swagger
             .requestMatchers(
                 "/swagger-ui/**",
                 "/swagger-ui.html",
                 "/v3/api-docs/**",
                 "/swagger-resources/**",
                 "/webjars/**",
                 "/Login/**"
             ).permitAll()
             // ✅ Allow login API
             .requestMatchers("/auth/**").permitAll()
             // 🔒 Protect all other APIs
             .anyRequest().authenticated()
         )
         // add JWT filter
         .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

    	 return http.build();
    }

    // Password encoder bean (for login)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication manager bean (used in AuthController)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
