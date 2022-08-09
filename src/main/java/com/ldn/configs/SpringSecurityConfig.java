/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ldn.configs.handlers.SigninSuccessHandler;
import com.ldn.configs.handlers.SignoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author three
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ldn.repository",
    "com.ldn.service"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationSuccessHandler signinSuccessHandler;
    
    @Autowired
    private LogoutSuccessHandler signoutSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "aleisterw",
                "api_key", "829342486126452",
                "api_secret", "Rtou-UwwUqPTqjrMJSTlCKZ1Lsw",
                "secure", true
        ));

        return cloudinary;
    }
    
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new SigninSuccessHandler();
    }
    
    @Bean
    public LogoutSuccessHandler signoutSuccessHandler() {
        return new SignoutSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/accounts")
                .usernameParameter("email")
                .passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/").failureUrl("/accounts?error");
        http.formLogin().successHandler(this.signinSuccessHandler);
        http.logout().logoutUrl("/signout");
        http.logout().logoutSuccessHandler(this.signoutSuccessHandler);
        http.exceptionHandling().accessDeniedPage("/accounts?acessDenied");
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/accounts").permitAll()
                .antMatchers("/accounts/**").hasAnyAuthority("CUSTOMER", "EMPLOYEE", "ADMIN")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN");
        http.csrf().disable();
    }
}
