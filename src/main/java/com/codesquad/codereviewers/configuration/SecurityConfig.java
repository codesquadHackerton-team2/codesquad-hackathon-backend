package com.codesquad.codereviewers.configuration;

import com.codesquad.codereviewers.configuration.security.CorsFilter;
import com.codesquad.codereviewers.configuration.security.GithubAuthenticationSuccessHandler;
import com.codesquad.codereviewers.configuration.security.providers.GithubAuthenticationProvider;
import com.codesquad.codereviewers.configuration.security.providers.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private GithubAuthenticationSuccessHandler successHandler;

    @Autowired
    private GithubAuthenticationProvider githubAuthenticationProvider;

    protected CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    protected PreAuthorizeFilter loginFilter() throws Exception {
        PreAuthorizeFilter filter = new PreAuthorizeFilter("/login", successHandler);
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(githubAuthenticationProvider);
    }

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

        http
                .authorizeRequests()
                .antMatchers("/login**").permitAll();

        http
                .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);

        http
                .addFilterBefore(corsFilter(), PreAuthorizeFilter.class);



    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
