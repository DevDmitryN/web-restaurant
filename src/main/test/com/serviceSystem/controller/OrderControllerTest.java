package com.serviceSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceSystem.config.*;
import com.serviceSystem.controller.util.MockUser;
import com.serviceSystem.entity.Notification;
import com.serviceSystem.entity.dto.AuthenticationRequestDto;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, RestSecurityConfig.class, WebConfig.class, WebInitializer.class,WebSocketConfig.class})
@WebAppConfiguration
public class OrderControllerTest {


    private Logger logger = LoggerFactory.getLogger(OrderControllerTest.class);

    private final String AUTH = "Authorization";
    private final String API_PREFIX = "/api/v1";

    private MockUser admin;
    private MockUser client;
    private MockUser waiter;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .apply(springSecurity())
                .build();
        admin = new MockUser("putin@rf.ru","1234",mockMvc,API_PREFIX);
        client = new MockUser("boris@britva.com","1234",mockMvc,API_PREFIX);
        waiter = new MockUser("gendolf@white.gray","1234",mockMvc,API_PREFIX);

    }


    @Test
    public void getDataForCreatingOrder() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String result = mockMvc.perform(get(API_PREFIX + "/orders/creating").header(AUTH,client.getToken()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        HashMap<String,Object> map = mapper.readValue(result, HashMap.class);
        logger.info(map.toString());
        assertNotNull(map);
    }


    @Test
    public void creatingOrderSuccessfully() throws Exception{

        JSONObject tableJSON = new JSONObject()
                .put("id",5)
                .put("capacity",12)
                .put("freeStatus",true);
        logger.info("Ordered table " + tableJSON);
        JSONObject dishJSON = new JSONObject()
                .put("id",4)
                .put("name", "Стейк из свинины")
                .put("description", "null")
                .put("price", 25);

        JSONObject dishInOrderJSON = new JSONObject()
                .put("dish",dishJSON)
                .put("amount",3);
        logger.info("Ordered dishes " + dishInOrderJSON);

        String bookingTimeBegin = "15-08-2019 18:00";
        String bookingTimeEnd = "15-08-2019 18:30";
        logger.info("Ordered time " + bookingTimeBegin + "; " + bookingTimeEnd);

        JSONObject orderJSON = new JSONObject()
                .put("table",tableJSON)
                .put("dishesInOrder",new JSONObject[]{dishInOrderJSON})
                .put("bookingTimeBegin",bookingTimeBegin)
                .put("bookingTimeEnd",bookingTimeEnd);

        mockMvc.perform(post(API_PREFIX + "/orders/creating")
                .header(AUTH,client.getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(orderJSON.toString())
        ).andExpect(status().isOk());

    }

    @Test
    public void getAllOrderSuccessfully() throws Exception{

    }



}
