package com.serviceSystem.websocket;

import com.serviceSystem.config.WebInitializer;
import com.serviceSystem.controller.OrderController;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInitializer.class})
@WebAppConfiguration
public class WebSocketTest {

    private MockMvc mockMvc;



}
