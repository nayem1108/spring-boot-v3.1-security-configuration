package com.nayem.securitytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    // InMemoryDatabase configuration instade of sql database
    @Bean
    public UserDetailsManager userDetailsManager(){
        UserDetails user = User.builder()
                                    .username("user")
                                    .password(passwordEncoder().encode("user"))
                                    .roles("NORMAL_USER")
                                    .build();
        UserDetails admin = User.builder()
                                .username("admin")
                                .password(passwordEncoder().encode("admin"))
                                .roles("ADMIN")
                                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // Security Configuration using SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/**").permitAll();
            // auth.requestMatchers("/**","/public/**").permitAll();
            auth.requestMatchers("/user/**").hasRole("USER");
            auth.requestMatchers("/admin/**").hasRole("ADMIN");
            auth.anyRequest().authenticated();
        })
        .csrf((AbstractHttpConfigurer::disable))
        .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    // PasswordEncoder for encode password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    
}
