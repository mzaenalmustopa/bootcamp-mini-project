package com.dsp.restapi.controller;

import com.dsp.restapi.model.entity.UserEntity;
import com.dsp.restapi.model.request.AddressRequest;
import com.dsp.restapi.model.request.ContactRequest;
import com.dsp.restapi.model.request.RegisterRequest;
import com.dsp.restapi.model.response.Response;
import com.dsp.restapi.repository.ContactRepository;
import com.dsp.restapi.repository.UserRepository;
import com.dsp.restapi.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
        userRepository.deleteAll();

        RegisterRequest request = new RegisterRequest("usertest","passwordtest","emailtest@gmail.com");
        userService.register(request);
    }

    @Test
    void testSaveContact() throws Exception {
        UserEntity user = userRepository.findAll().get(0);
        List<AddressRequest> address = new ArrayList<>();
        ContactRequest request = new ContactRequest(
                user.getId(), "firstName", "lastName","email@gmail.com", "companyName", "081345677",address
        );

        mockMvc.perform(post("/api/contact")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            Response response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Response>() {
            });
        });
    }
}