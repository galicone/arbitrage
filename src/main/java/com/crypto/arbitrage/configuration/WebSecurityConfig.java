package com.crypto.arbitrage.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/register", "/webjars/**", "/css/**", "/js/**", "/images/**", "/fonts/material-design-icons/**", "/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/home")
                .defaultSuccessUrl("/main")
                .usernameParameter("username")
            	.passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    		throws Exception {
    	auth.
    		jdbcAuthentication()
    			.usersByUsernameQuery("select username, password, enabled from public.user where username=?")
    			.authoritiesByUsernameQuery("select username, 'ROLE_USER' from public.user where username=?")
    			.dataSource(dataSource);
    }
}

