package com.itransition.mikrise2.demo.configs;

import com.itransition.mikrise2.demo.OAuth2.CustomOAuth2User;
import com.itransition.mikrise2.demo.OAuth2.CustomOAuth2UserService;
import com.itransition.mikrise2.demo.services.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final CustomOAuth2UserService oauthUserService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .logout()
                .and()
                .authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/feed")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler((request, response, authentication) -> {

                    CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                    userDetailsServiceImpl.processOAuthPostLogin(oauthUser.getName());

                    response.sendRedirect("/feed");
                })
                .and()
                .csrf()
                .disable();

    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
    }
}