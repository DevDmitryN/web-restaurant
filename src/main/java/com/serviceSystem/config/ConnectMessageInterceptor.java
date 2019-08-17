package com.serviceSystem.config;

import com.serviceSystem.config.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.util.LinkedList;
import java.util.Objects;

@Component
public class ConnectMessageInterceptor implements ChannelInterceptor {
    private Logger logger = LoggerFactory.getLogger(ConnectMessageInterceptor.class);
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,StompHeaderAccessor.class);
        if(StompCommand.CONNECT.equals(Objects.requireNonNull(accessor).getCommand())){
            //String token = (String) message.getHeaders().get("Authorization");
            String token =
                    ((LinkedMultiValueMap<String, String>) message.getHeaders().get("nativeHeaders"))
                            .get("Authorization")
                            .get(0);

            try{
                token = token.substring(7, token.length());
                if(jwtTokenProvider.validateToken(token)){
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    if(authentication != null){
                        if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("WAITER"))){
                            throw new AccessDeniedException("Access for user " + authentication.getDetails() + " denied");
                        }
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }catch (UsernameNotFoundException | AccessDeniedException e){
                logger.warn(e.getMessage());
            }
        }
        return message;
    }
}
