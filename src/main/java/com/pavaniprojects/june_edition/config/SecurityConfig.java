package com.pavaniprojects.june_edition.config;

import com.pavaniprojects.june_edition.User;
import com.pavaniprojects.june_edition.UserRepository;
import com.pavaniprojects.june_edition.security.CustomUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //we donot have register option yet, then when no users are present in db while craeting application
    //Insert one admin when the application starts.
    @Bean
    CommandLineRunner init(UserRepository userRepository,  PasswordEncoder passwordEncoder){

        return args -> {

            if(userRepository.findByUsername("admin").isEmpty()){

                User admin = new User();

                admin.setUsername("admin");

                admin.setPassword(
                        passwordEncoder.encode("admin123")
                );

                admin.setRole("ADMIN");

                userRepository.save(admin);

            }

        };
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager(); //returns providerManager object
    }


//without security filter chain, all endpoints are protected..i.e..,auth endpoint is alos protected
    //login must be accessible without a token

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(customUserDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}