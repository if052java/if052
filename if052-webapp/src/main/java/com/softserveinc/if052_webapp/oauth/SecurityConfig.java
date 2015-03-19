package com.softserveinc.if052_webapp.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// @formatter:off
//    	    http.authorizeRequests()
//                //.antMatchers("/profile").hasRole("USER")
//                .anyRequest().permitAll()
//                .and()
//            .logout()
//                .logoutSuccessUrl("/login")
//                //.logoutUrl("/logout") // by the default logoutUrl requires POST method
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .permitAll()
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login.do")
//                .failureUrl("/login?authentication_error=true")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                .permitAll();
//    	// @formatter:on
//	}
//
//}
