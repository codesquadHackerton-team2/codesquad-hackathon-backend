package com.codesquad.codereviewers.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();

        http
                .authorizeRequests()
                .antMatchers("/api/v1/article/get/**").permitAll();

        http
                .authorizeRequests()
                .antMatchers("/h2-console**").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
