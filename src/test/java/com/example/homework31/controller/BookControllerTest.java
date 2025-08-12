package com.example.homework31.controller;

import com.example.homework31.service.BookEntityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BookEntityService bookEntityService;


    @Test
    @DisplayName("GET /books — запрещено без авторизации")
    void getAllBooks_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /book/{id} — запрещено без авторизации")
    void getBookById_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/book/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /book — запрещено без авторизации")
    void createBook_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/book").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("PUT /book — запрещено без авторизации")
    void updateBook_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(put("/book").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("DELETE /book/{id} — запрещено без авторизации")
    void deleteBook_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(delete("/book/1").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("GET /books — доступно авторизованным")
    void getAllBooks_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("GET /book/{id} — доступно авторизованным")
    void getBookById_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/book/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("POST /book — доступно авторизованным")
    void createBook_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/book")
                        .param("title", "Test Book")
                        .param("genre", "test")
                        .param("author", "Test Author")
                        .param("comment", "published")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("PUT /book — доступно авторизованным")
    void updateBook_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(put("/book")
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Book\"}")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("DELETE /book/{id} — доступно авторизованным")
    void deleteBook_authenticated_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/book/1").with(csrf()))
                .andExpect(status().isNoContent());
    }
}