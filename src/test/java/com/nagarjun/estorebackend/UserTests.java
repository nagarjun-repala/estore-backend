package com.nagarjun.estorebackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.nagarjun.estorebackend.mockData.UserData;


@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {
    
    @Autowired
    UserData userData;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        userData.setup();
    }

    // @AfterEach
    // void clear(){
    //     userData.clear();
    // }

    @Test
    public void createUser() throws Exception {
        System.out.println("Hello" + userData.insertUser());

        RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userData.insertUser());

        mockMvc.perform(request).andExpect(status().isCreated());
    }
    @Test
    public void getUserByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/user/{id}", 2);
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(userData.getUser().getId()))
            .andExpect(jsonPath("$.firstName").value(userData.getUser().getFirstName()))
            .andExpect(jsonPath("$.lastName").value(userData.getUser().getLastName()))
            .andExpect(jsonPath("$.username").value(userData.getUser().getUsername()))
            .andExpect(jsonPath("$.email").value(userData.getUser().getEmail()))
            .andExpect(jsonPath("$.createdOn").value(userData.getUser().getCreatedOn()));
        
    }

    @Test
    public void getUserByUsername() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/user/username/{username}", "test1");
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(userData.getUser().getId()))
            .andExpect(jsonPath("$.firstName").value(userData.getUser().getFirstName()))
            .andExpect(jsonPath("$.lastName").value(userData.getUser().getLastName()))
            .andExpect(jsonPath("$.username").value(userData.getUser().getUsername()))
            .andExpect(jsonPath("$.email").value(userData.getUser().getEmail()))
            .andExpect(jsonPath("$.createdOn").value(userData.getUser().getCreatedOn()));        
        
    }

}
