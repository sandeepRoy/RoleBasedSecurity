package com.sandeep.securityone.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.sandeep.securityone.entities.Permission.*;
import static com.sandeep.securityone.entities.Role.ADMIN;
import static com.sandeep.securityone.entities.Role.MANAGER;
import static com.sandeep.securityone.entities.Role.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Autowired
    public JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    public AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**") // whitelists
                    .permitAll()

                // management apis' available for CRUD to admin, CRU to manager
                .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                .requestMatchers(HttpMethod.POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/api/v1/management/**").hasAuthority(ADMIN_DELETE.name())

                // user api's available fro CRUD to admin, CRU to user
                .requestMatchers("/api/v1/user/**").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/user/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                .requestMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").hasAnyAuthority(ADMIN_UPDATE.name(), USER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasAuthority(ADMIN_DELETE.name())


                .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

    /*
    * Authorities:
    *
    * 1. ADMIN has Create - Read - Update - Delete, access to MANAGEMENT, USER & ADMIN
    *
    * 2. MANGEMENT has CREATE - READ - UPDATE, access to MANAGEMENT
    *
    * 3. USER has CREATE - READ - UPDATE, access to USER
    *
    * 4. Limitations - User & Manager can't delete themselves, Admin only can do it
    *
    * */
}
