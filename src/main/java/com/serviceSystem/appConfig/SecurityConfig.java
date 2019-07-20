package com.serviceSystem.appConfig;

import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.UserDetailsSpringService;
import com.serviceSystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.serviceSystem")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        System.out.println(http);
        http.addFilterBefore(filter, CsrfFilter.class)
                .authorizeRequests()
                .antMatchers("/403").permitAll()
                .antMatchers("/orders/success").permitAll()
                .antMatchers("/").access("hasRole('CLIENT') or hasRole('WAITER')")
                .antMatchers("/list").access("hasRole('ADMIN') or hasRole('WAITER')")
                .antMatchers("/orders/creating").access("hasRole('CLIENT')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/authorization")
                .usernameParameter("email")
                .defaultSuccessUrl("/",true)
                .failureUrl("/authorization?error=error").permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/authorization?logout=true").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth){
//        try{
//            auth.inMemoryAuthentication().withUser("user")
//                    .password("user").roles("CLIENT");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
