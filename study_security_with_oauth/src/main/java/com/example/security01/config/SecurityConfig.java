package com.example.security01.config;

import com.example.security01.config.oauth.PrincipalOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true) // https://naver.me/FZ8xPMXB 참고
public class SecurityConfig {
    @Autowired
    private PrincipalOAuth2UserService principalOAuth2UserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())

            /*
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/user/**").authenticated();
                    auth.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    auth.anyRequest().permitAll();
                })
            */
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
            )

            .formLogin(form -> form
                .loginPage("/loginForm")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/")
                .permitAll()
            )

            .oauth2Login(oauth2 -> oauth2
                .loginPage("/loginForm")
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(principalOAuth2UserService)
                )
            );

        return http.build();
    }
}


// ↓ 과거 방식(WebSecurityConfigurerAdapter) - Deprecated
/*
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Bean
        public BCryptPasswordEncoder encodePwd() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.authorizeRequests()
                        .antMatchers("/user/**").authenticated()
                        .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                        .anyRequest().permitAll()
                        .and()
                    .formLogin()
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/")
                        .permitAll()
                        .and()
                    .oauth2Login()
                        .loginPage("/loginForm")
                        .userInfoEndpoint()
                        .userService(principalOAuth2UserService);

        }
    }
*/