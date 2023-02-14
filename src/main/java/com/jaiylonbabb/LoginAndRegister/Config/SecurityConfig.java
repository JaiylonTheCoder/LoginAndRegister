package com.jaiylonbabb.LoginAndRegister.Config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Resource
//    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/login", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
                .requestMatchers("/users/addNew").permitAll()
//                .requestMatchers("/users").hasAnyAuthority("Admin")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .formLogin().defaultSuccessUrl("/home").permitAll()
//                .authenticationDetailsSource(authenticationDetailsSource)
                .and()
                .logout().logoutUrl("/logout");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);

        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }
}
