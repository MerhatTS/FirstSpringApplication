package com.example.FirstSpringApplication.Config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authorization) -> {
            authorization.requestMatchers("/movie/edit/{id}").hasRole("user");
            authorization.requestMatchers("/movie/delete/{id}").hasRole("admin");
            authorization.anyRequest().permitAll();

        });

            httpSecurity.formLogin((Customizer.withDefaults()));
        return httpSecurity.build();

    }
}

