package com.example.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//                .and()
                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**")
//                .permitAll()
                .antMatchers("/api/**").permitAll();
    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("https://localhost", "localhost"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
