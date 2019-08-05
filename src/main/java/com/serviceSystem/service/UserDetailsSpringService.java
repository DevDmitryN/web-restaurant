package com.serviceSystem.service;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.User;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsSpringService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserDetailsSpringService.class);
    @Autowired
    ClientService clientService;
    @Autowired
    WorkerService workerService;

    public User getUserByEmail(String email){
        User user = clientService.getByEmail(email);
        if(user != null){
            return user;
        }
        user = workerService.getByEmail(email);
        if(user != null){
            return ((Worker) user).isInStaff() ? user : null;
        }
        return null;
    }
    public Set<GrantedAuthority> getRole(User user){
        Set<GrantedAuthority> roles = new HashSet<>();
        if(user instanceof Client){
            roles.add(new SimpleGrantedAuthority("CLIENT"));
            return roles;
        }
        if(user instanceof Worker){
            Worker worker = (Worker) user;
            switch (worker.getRole()){
                case WAITER:
                    roles.add(new SimpleGrantedAuthority(Role.WAITER.name()));
                    break;
                case ADMIN:
                    roles.add(new SimpleGrantedAuthority(Role.WAITER.name()));
                    roles.add(new SimpleGrantedAuthority(Role.ADMIN.name()));
                    break;
            }
        }
        return roles;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        if(user == null){
            logger.info("user with email " + email + " not found");
            throw new UsernameNotFoundException("user with email" + email + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),getRole(user));
    }
}
