package com.corekcioglu.blocking.exception.translation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable() // Without disabling this, the server returns a 403 error with CSRF Token has been associated to this client
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET, "/**").hasAnyRole("admin")
                .anyExchange().authenticated()
                .and().httpBasic()
                .and().formLogin().disable()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        final UserDetails admin = User.withUsername("admin")
                .password("admin")
                .roles("admin")
                .build();
        return new MapReactiveUserDetailsService(List.of(admin));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
