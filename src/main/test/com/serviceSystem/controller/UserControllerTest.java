package com.serviceSystem.controller;

import com.serviceSystem.config.ApplicationConfig;
import com.serviceSystem.config.RestSecurityConfig;
import com.serviceSystem.config.WebConfig;
import com.serviceSystem.config.WebSocketConfig;
import com.serviceSystem.entity.dto.AuthenticationRequestDto;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, RestSecurityConfig.class, WebConfig.class, WebSocketConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private AuthenticationRequestDto admin;
    private AuthenticationRequestDto client;
    private AuthenticationRequestDto waiter;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .apply(springSecurity())
                .build();
        admin = new AuthenticationRequestDto("putin@rf.ru","1234");
        client = new AuthenticationRequestDto("boris@britva.com","1234");
        waiter = new AuthenticationRequestDto("gendolf@lord.ring","1234");
    }
    public String getToken(AuthenticationRequestDto user) throws Exception{
        JSONObject jsonObject = new JSONObject()
                .put("email",user.getEmail())
                .put("password",user.getPassword());
        String result = mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString())
        ).andExpect(status().isOk()).andReturn().getResponse().getHeader("token");
        assertNotEquals("",result);
        return result;
    }
    @Test
    public void obtainAccessTokenTest() throws Exception{
        String waiterToken = getToken(waiter);
        logger.info("waiter token: " + waiterToken);
        String adminToken = getToken(admin);
        logger.info("admin token: " + adminToken);
        String clientToken = getToken(client);
        logger.info("client token: " + clientToken);
    }
}
