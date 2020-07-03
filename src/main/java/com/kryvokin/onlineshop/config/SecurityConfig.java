package com.kryvokin.onlineshop.config;

import com.kryvokin.onlineshop.service.AuthenticationSuccessHandlerImpl;
import com.kryvokin.onlineshop.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder bCryptPasswordEncode;
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder bCryptPasswordEncode,
                          AuthenticationSuccessHandlerImpl authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncode = bCryptPasswordEncode;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncode);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .passwordParameter("password")
                .usernameParameter("email")
                .successHandler(authenticationSuccessHandler)
                .failureUrl("/login-error")
                .and()
                .logout().deleteCookies("JSESSIONID").logoutUrl("/logout").permitAll().logoutSuccessUrl("/login")
                .and()
                .rememberMe().rememberMeParameter("remember-me").userDetailsService(userDetailsService).key("unique")
                .and().sessionManagement().maximumSessions(2).expiredUrl("/");
    }
}
