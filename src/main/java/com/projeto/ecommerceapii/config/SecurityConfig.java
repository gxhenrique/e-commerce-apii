package com.projeto.ecommerceapii.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http
                .csrf( csrf -> csrf.disable())
                .cors(cors -> cors.configure(http))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(athorize -> athorize
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        //publicas
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        //adm
                        .requestMatchers(HttpMethod.POST, "/api/produto/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/produto/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/produto/**").hasRole("ADMIN")
                        //adm
                        .requestMatchers(HttpMethod.POST, "/api/categoria/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/categoria/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/categoria/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }


}
