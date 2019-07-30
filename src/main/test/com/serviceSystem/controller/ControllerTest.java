package com.serviceSystem.controller;

import com.serviceSystem.config.ApplicationConfig;
import com.serviceSystem.config.SecurityConfig;
import com.serviceSystem.config.WebConfig;
import jdk.jfr.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class, WebConfig.class})
@WebAppConfiguration
public class ControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TestController testController;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void test() throws Exception {
            mockMvc.perform(get("/test"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("test"));
    }
    @Test
    public void getTestClient() throws Exception{
        mockMvc.perform(get("/test-client").accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
