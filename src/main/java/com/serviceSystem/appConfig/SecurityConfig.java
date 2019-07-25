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
        http.addFilterBefore(filter, CsrfFilter.class)
                .authorizeRequests()
                .antMatchers("/403").permitAll()
                .antMatchers("/order/creating/success").permitAll()
                .antMatchers("/user/signUpClient").anonymous()
                //access for client
                .antMatchers("/order/creating").access("hasRole('CLIENT')")
                .antMatchers("/orders/active").hasAuthority("CLIENT")
                .antMatchers("/client/{userId}/orders/active/{orderId}/cancel").hasAuthority("CLIENT")
                .antMatchers("/client").hasAnyAuthority("CLIENT")
                //access for client and waiter
                .antMatchers("/").access("hasRole('CLIENT') or hasRole('WAITER')")
                //access for waiter
                .antMatchers("/orders/list").hasAuthority("WAITER")
                .antMatchers("/order/{order_id}").hasAuthority("WAITER")
                .antMatchers("/order/delete/{order_id}").hasAuthority("WAITER")
                .antMatchers("/order/{order_id}/setWorker").hasAuthority("WAITER")
                .antMatchers("/tables/list").hasAuthority("WAITER")
                .anyRequest().authenticated()
                //authorization
                .and()
                .formLogin()
                .loginPage("/user/authorization")
                .usernameParameter("email")
                .defaultSuccessUrl("/",true)
                .failureUrl("/user/authorization?error=error").permitAll()
                .and()
                //logout
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/user/authorization?logout=true").permitAll()
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
