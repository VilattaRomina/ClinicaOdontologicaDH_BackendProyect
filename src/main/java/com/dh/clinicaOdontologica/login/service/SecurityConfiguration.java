package com.dh.clinicaOdontologica.login.service;

import com.dh.clinicaOdontologica.login.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/pacientes/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/odontologos/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/pacientes/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/odontologos/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/pacientes/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/odontologos/**").hasAuthority("ADMIN")
                .antMatchers("/turnos/**").authenticated()
                .antMatchers("/alta-odontologos.html", "/odontologos.html", "/alta-pacientes.html", "/pacientes.html").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}


