package com.serviceSystem.config;

import com.serviceSystem.config.jwt.JwtConfigurer;
import com.serviceSystem.config.jwt.JwtTokenProvider;
import com.serviceSystem.service.UserDetailsSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@EnableWebSecurity
@ComponentScan(basePackages = {"com.serviceSystem.config.jwt","com.serviceSystem.service","com.serviceSystem.controller"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsSpringService userDetailsSpringService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    } //TODO length

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsSpringService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/users/login").permitAll()
                //orders
                .antMatchers(HttpMethod.GET,"/orders").hasAuthority("WAITER")
                .antMatchers(HttpMethod.GET,"/orders/{orderId}").hasAnyAuthority("CLIENT","WAITER")
                .antMatchers(HttpMethod.DELETE,"/orders/{orderId}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/clients/{clientId}/orders").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET,"/orders/creating").hasAuthority("CLIENT")
                //workers
                .antMatchers(HttpMethod.GET,"/workers/staff","/workers/{workerId}").hasAuthority("WAITER")
                .antMatchers(HttpMethod.GET,"/workers").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/workers").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/workers/{id}").hasAuthority("ADMIN")
                //clients
                .antMatchers(HttpMethod.POST,"/clients").permitAll()
                .antMatchers(HttpMethod.GET,"/clients/{clientId}").hasAnyAuthority("CLIENT","WAITER")
                .antMatchers(HttpMethod.GET,"/clients").hasAuthority("WAITER")
                .antMatchers(HttpMethod.PUT,"/clients/{clientId}").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.PATCH,"/clients/{clientId}").hasAuthority("CLIENT")
                //tables
                .antMatchers(HttpMethod.GET,"/tables","/tables/{tableId}").permitAll()
                .antMatchers(HttpMethod.POST,"tables").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/tables/{id}").hasAuthority("ADMIN")
                //dishes
                .antMatchers(HttpMethod.GET,"/menu").permitAll()
                .antMatchers(HttpMethod.GET,"/dishes","/dishes/{dishId}").hasAuthority("WAITER")
                .antMatchers(HttpMethod.POST,"/dishes").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"dishes/{id}").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));

    }
}
