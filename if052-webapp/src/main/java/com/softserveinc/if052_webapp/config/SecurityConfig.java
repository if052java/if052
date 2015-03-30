package com.softserveinc.if052_webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/addUser").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/**").hasRole("USER")
//                .anyRequest().permitAll()
            .and() //.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("/**")).disable()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"))
                .logoutSuccessUrl("/login.jsp")
                .permitAll()
            .and()
                .formLogin()
                .loginProcessingUrl("/login.do")
                .loginPage("/login.jsp")
                .failureUrl("/login.jsp?authentication_error=true")
                .permitAll();
        // @formatter:on
    }

}
