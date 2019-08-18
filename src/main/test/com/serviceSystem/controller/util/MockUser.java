package com.serviceSystem.controller.util;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MockUser {
    private String email;
    private String password;
    private String token;
    private MockMvc mockMvc;

    public MockUser(String email, String password, MockMvc mockMvc, String urlPrefix) throws Exception{
        this.email = email;
        this.password = password;
        this.mockMvc = mockMvc;
        this.token = obtainToken(urlPrefix);
    }

    private String obtainToken(String urlPrefix) throws Exception{
        JSONObject jsonObject = new JSONObject()
                .put("email",email)
                .put("password",password);
        String result = mockMvc.perform(post(urlPrefix + "/users/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString())
        ).andExpect(status().isOk()).andReturn().getResponse().getHeader("token");
        assertNotNull(result);
        assertNotEquals("",result);
        return "Bearer " + result;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
