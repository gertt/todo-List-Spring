package com.todolist.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TetdContollerunoTest {

    @Autowired
    TetdContolleruno rateController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TetdContolleruno tetdContolleruno;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(rateController).build();
    }

    @Test
    public void showItdfdfdfem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/show-itdem").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}