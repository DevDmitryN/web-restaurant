package com.serviceSystem.config;

import com.serviceSystem.config.jwt.JwtConfigurer;
import com.serviceSystem.config.jwt.JwtTokenProvider;
import com.serviceSystem.service.UserDetailsSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.serviceSystem.config.jwt","com.serviceSystem.service","com.serviceSystem.controller"})
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsSpringService userDetailsSpringService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsSpringService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/api/v1/**")
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/v1/users/login").permitAll()
                //orders
                .antMatchers(HttpMethod.GET,"/api/v1/orders/creatingForm").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET,"/api/v1/orders").hasAuthority("WAITER")
                .antMatchers(HttpMethod.GET,"/api/v1/orders/{orderId}").access("@securityService.hasAccessToOrder(authentication,#orderId) or hasAuthority('WAITER')")
                .antMatchers(HttpMethod.DELETE,"/api/v1/orders/{orderId}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/clients/{clientId}/orders").access("@securityService.validClientId(authentication,#clientId) or hasAuthority('WAITER')")
                .antMatchers(HttpMethod.POST,"/api/v1/orders").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.PUT,"/api/v1/orders/{orderId}/dishes").access("hasAuthority('CLIENT') and @securityService.hasAccessToOrder(authentication,#orderId)"  )
                .antMatchers(HttpMethod.PUT,"/api/v1/orders/{orderId}").access("@securityService.hasAccessToOrder(authentication,#orderId) or hasAuthority('WAITER')")
                //workers
                .antMatchers(HttpMethod.PUT,"/api/v1/orders/active/{orderId}/worker").hasAuthority("WAITER")
                .antMatchers(HttpMethod.GET,"/api/v1/workers/staff","/api/v1/workers/{workerId}").hasAuthority("WAITER")
                .antMatchers(HttpMethod.GET,"/api/v1/workers").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/workers").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/v1/workers/{workerId}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH,"/api/v1/workers/{workerId}").hasAuthority("ADMIN")
                //clients
                .antMatchers(HttpMethod.POST,"/api/v1/clients").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/clients/{clientId}").access("@securityService.validClientId(authentication,#clientId) or hasAuthority('WAITER')")
                .antMatchers(HttpMethod.GET,"/api/v1/clients").hasAuthority("WAITER")
                .antMatchers(HttpMethod.PUT,"/api/v1/clients/{clientId}").access("@securityService.validClientId(authentication,#clientId)")
                .antMatchers(HttpMethod.PATCH,"/api/v1/clients/{clientId}").access("@securityService.validClientId(authentication,#clientId)")
                //tables
                .antMatchers(HttpMethod.GET,"/api/v1/tables","/api/v1/tables/{tableId}").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/tables").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/v1/tables/{tableId}").hasAuthority("ADMIN")
                //dishes
                .antMatchers(HttpMethod.GET,"/api/v1/menu").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/menu/{dishId}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/dishes","/api/v1/dishes/{dishId}").hasAuthority("WAITER")
                .antMatchers(HttpMethod.POST,"/api/v1/dishes").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/v1/dishes/{dishId}").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));

    }
}

