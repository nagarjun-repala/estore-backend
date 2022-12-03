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
    public void validUserCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userData.insertValidUser());

        mockMvc.perform(request).andExpect(status().isCreated());
    }
    @Test
    public void validGetUserById() throws Exception {
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
    public void validGetUserByUsername() throws Exception {
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

    @Test
    public void invalidGetUserById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/user/{id}", 10);
        mockMvc.perform(request)
            .andExpect(status().isNotFound());
        
    }

    @Test
    public void invalidGetUserByUsername() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/user/username/{username}", "test10");
        mockMvc.perform(request)
            .andExpect(status().isNotFound());
    }    

    @Test
    public void invalidUserCreation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userData.insertInvalidUser());
        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    public void validUserUpdate() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/user/{id}", 2)
            .contentType(MediaType.APPLICATION_JSON)
            .content(userData.insertValidUser());
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void invalidUserUpdate() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/user/{id}", 2)
            .contentType(MediaType.APPLICATION_JSON)
            .content(userData.insertInvalidUser());
        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    public void validUserDeletion() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/user/{id}", 2);
        mockMvc.perform(request).andExpect(status().isNoContent());
    }

    @Test
    public void invalidUserDeletion() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/user/{id}", 5);
        mockMvc.perform(request).andExpect(status().isNotFound());
    }
}
