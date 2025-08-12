package com.example.homework31.controller;

import com.example.homework31.service.AuthorEntityService;
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

@WebMvcTest(AuthorController.class)
@AutoConfigureMockMvc
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AuthorEntityService authorEntityService;

    @Test
    @DisplayName("GET /authors — запрещено без авторизации")
    void getAllAuthors_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /author/id/{id} — запрещено без авторизации")
    void getAuthorById_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/author/id/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /author — запрещено без авторизации")
    void createAuthor_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/author").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("PUT /author — запрещено без авторизации")
    void updateAuthor_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(put("/author").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("DELETE /author/{id} — запрещено без авторизации")
    void deleteAuthor_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(delete("/author/1").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("GET /authors — доступно авторизованным")
    void getAllAuthors_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("GET /author/id/{id} — доступно авторизованным")
    void getAuthorById_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/author/id/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("POST /author — доступно авторизованным")
    void createAuthor_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/author")
                        .param("name", "Test author")
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("DELETE /author/{id} — доступно авторизованным")
    void deleteAuthor_authenticated_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/author/1").with(csrf()))
                .andExpect(status().isNoContent());
    }
}