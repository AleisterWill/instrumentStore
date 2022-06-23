/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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

//    @Autowired
//    private UserDetailsService userDetailsService;

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

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/signin")
                .usernameParameter("email")
                .passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/").failureUrl("/signin?error");
        http.logout().logoutUrl("/signout");
        http.logout().logoutSuccessUrl("/signin");
        http.exceptionHandling().accessDeniedPage("/signin?acessDenied");
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin").hasAuthority("@USER_ADMIN@")
                .antMatchers("/admin/**").hasAuthority("@USER_ADMIN@");
        http.csrf().disable();
    }
}
