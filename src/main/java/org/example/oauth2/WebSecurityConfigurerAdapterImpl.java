package org.example.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfigurerAdapterImpl {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                    .requestMatchers("/cities").permitAll()
                    .requestMatchers("/secure/**").authenticated()
                    .anyRequest().authenticated()
            )
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll
            )
            .logout(LogoutConfigurer::permitAll)
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails readUser = User.withDefaultPasswordEncoder()
                                   .username("reader")
                                   .password("password")
                                   .roles("READ")
                                   .build();

        UserDetails writeUser = User.withDefaultPasswordEncoder()
                                    .username("writer")
                                    .password("password")
                                    .roles("WRITE")
                                    .build();

        UserDetails deleteUser = User.withDefaultPasswordEncoder()
                                     .username("deleter")
                                     .password("password")
                                     .roles("DELETE")
                                     .build();

        UserDetails multiRoleUser = User.withDefaultPasswordEncoder()
                                        .username("multi")
                                        .password("password")
                                        .roles("READ", "WRITE")
                                        .build();

        UserDetails adminUser = User.withDefaultPasswordEncoder()
                                    .username("admin")
                                    .password("password")
                                    .roles("READ", "WRITE", "DELETE")
                                    .build();

        return new InMemoryUserDetailsManager(readUser, writeUser, deleteUser, multiRoleUser, adminUser);
    }
}
