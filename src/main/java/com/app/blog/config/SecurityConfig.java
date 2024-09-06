package com.app.blog.config;

import com.app.blog.controller.impl.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserController userController;

    public SecurityConfig(UserController userController) {
        this.userController = userController;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(HttpMethod.DELETE, "/auth/**").hasRole("ADMIN")
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/blogs/**", "/tags/**").permitAll()
                                .anyRequest()
                                .authenticated()
                ).addFilterBefore(jwtFilter(userController), UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public JwtFilter jwtFilter(UserController userController) {
        return new JwtFilter(userController);
    }
}
