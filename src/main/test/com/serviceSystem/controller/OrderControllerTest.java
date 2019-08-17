package com.serviceSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceSystem.config.ApplicationConfig;
import com.serviceSystem.config.RestSecurityConfig;
import com.serviceSystem.config.WebConfig;
import com.serviceSystem.config.WebSocketConfig;
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
@ContextConfiguration(classes = {ApplicationConfig.class, RestSecurityConfig.class, WebConfig.class, WebSocketConfig.class})
@WebAppConfiguration
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = {ApplicationConfig.class, SecurityConfig.class, WebConfig.class, WebSocketConfig.class})
public class OrderControllerTest {


    private Logger logger = LoggerFactory.getLogger(OrderControllerTest.class);

//    @Autowired
//    Environment environment;

//    String p = environment.getProperty("local.server.port");
    private SockJsClient sockJsClient;
    private WebSocketStompClient stompClient;
    private WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    private final String AUTH = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    private AuthenticationRequestDto admin;
    private AuthenticationRequestDto client;
    private AuthenticationRequestDto waiter;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;




//    @InjectMocks
//    private OrderController orderController;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .apply(springSecurity())
                .build();
        admin = new AuthenticationRequestDto("putin@rf.ru","1234");
        client = new AuthenticationRequestDto("boris@britva.com","1234");
        waiter = new AuthenticationRequestDto("gendolf@white.gray","1234");

        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        this.sockJsClient = new SockJsClient(transports);

        this.stompClient = new WebSocketStompClient(sockJsClient);
        this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }


    @Test
    public void getDataForCreatingOrder() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String result = mockMvc.perform(get("/orders/creating"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        Map<String,Object> map = mapper.readValue(result, HashMap.class);
        assertNotNull(map);
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
    public void creatingOrderTest() throws Exception{

        AtomicReference<Throwable> failure = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);

        SessionHandler sessionHandler = new SessionHandler(failure);

        String clientToken = getToken(client);
        String adminToken = getToken(admin);


        this.headers.add(AUTH,TOKEN_PREFIX + adminToken);
        this.stompClient.connect("ws://localhost:{port}/ws", this.headers, sessionHandler, 8080);

        JSONObject tableJSON = new JSONObject()
                .put("id",5)
                .put("capacity",12)
                .put("freeStatus",true);

        JSONObject dishJSON = new JSONObject()
                .put("id",4)
                .put("name", "Стейк из свинины")
                .put("description", "null")
                .put("price", 25);

        JSONObject dishInOrderJSON = new JSONObject()
                .put("dish",dishJSON)
                .put("amount",3);

        String bookingTimeBegin = "15-08-2019 18:00";
        String bookingTimeEnd = "15-08-2019 18:30";

        JSONObject orderJSON = new JSONObject()
                .put("table",tableJSON)
                .put("dishesInOrder",new JSONObject[]{dishInOrderJSON})
                .put("bookingTimeBegin",bookingTimeBegin)
                .put("bookingTimeEnd",bookingTimeEnd);

        mockMvc.perform(post("/orders/creating")
                .header(AUTH,TOKEN_PREFIX + clientToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(orderJSON.toString())
        ).andExpect(status().isOk());

        if (latch.await(7, TimeUnit.SECONDS)) {
            if (failure.get() != null) {
                throw new AssertionError("", failure.get());
            }
        }
        else {
            fail("Greeting not received");
        }

    }

    @Test
    public void getAllTest() throws Exception{
        String token = getToken(admin);
    }

    class SessionHandler extends TestSessionHandler{

        CountDownLatch latch;
        AtomicReference<Throwable> failure;

        public SessionHandler(AtomicReference<Throwable> failure) {
            super(failure);
            this.failure = failure;
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            session.subscribe("/topic/notification", new StompFrameHandler() {
                @Override
                public Type getPayloadType(StompHeaders stompHeaders) {
                    return Notification.class;
                }

                @Override
                public void handleFrame(StompHeaders stompHeaders, Object o) {
                    Notification notification = (Notification) o;
                    try{
                        assertEquals("New order", notification.getContent());
                    }catch (Throwable e){
                        failure.set(e);
                    }finally {
                        session.disconnect();
                        latch.countDown();
                    }
                }
            });
//            try {
//                session.send("/app/hello", new HelloMessage("Spring"));
//            } catch (Throwable t) {
//                failure.set(t);
//                latch.countDown();
//            }
        }

        public CountDownLatch getLatch() {
            return latch;
        }

        public void setLatch(CountDownLatch latch) {
            this.latch = latch;
        }

    }

    private class TestSessionHandler extends StompSessionHandlerAdapter {

        private final AtomicReference<Throwable> failure;


        public TestSessionHandler(AtomicReference<Throwable> failure) {
            this.failure = failure;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            this.failure.set(new Exception(headers.toString()));
        }

        @Override
        public void handleException(StompSession s, StompCommand c, StompHeaders h, byte[] p, Throwable ex) {
            this.failure.set(ex);
        }

        @Override
        public void handleTransportError(StompSession session, Throwable ex) {
            this.failure.set(ex);
        }
    }

}
