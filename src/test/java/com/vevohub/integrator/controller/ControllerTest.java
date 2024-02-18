package com.vevohub.integrator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void anyoneCanViewPublicEndpoint() throws Exception {
        api.perform(get("/")).andExpect(status().isOk()).andExpect(content().string(containsStringIgnoringCase("Hello")));
    }

    @Test
    void notLoggedIn_shouldNotSeeSecuredEndpoint() throws Exception {
        api.perform(get("/test")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void loggedIn_shouldNotSeeSecuredEndpoint() throws Exception {
        api.perform(get("/test")).andExpect(status().isOk()).andExpect(content().string(containsStringIgnoringCase("1")));
    }
}
