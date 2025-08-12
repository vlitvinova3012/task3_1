package com.example.homework31.controller;

import com.example.homework31.service.GenreEntityService;
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

@WebMvcTest(GenreController.class)
@AutoConfigureMockMvc
class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private GenreEntityService genreEntityService;

    @Test
    @DisplayName("GET /genres — запрещено без авторизации")
    void getAllGenres_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/genres"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /genre/id/{id} — запрещено без авторизации")
    void getGenreById_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/genre/id/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /genre — запрещено без авторизации")
    void createGenre_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/genre").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("PUT /genre — запрещено без авторизации")
    void updateGenre_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(put("/genre").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("DELETE /genre/{id} — запрещено без авторизации")
    void deleteGenre_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(delete("/genre/1").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("GET /genres — доступно авторизованным")
    void getAllGenres_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("GET /genre/id/{id} — доступно авторизованным")
    void getGenreById_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/genre/id/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("POST /genre — доступно авторизованным")
    void createGenre_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/genre")
                        .param("name", "Test genre")
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("DELETE /genre/{id} — доступно авторизованным")
    void deleteGenre_authenticated_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/genre/1").with(csrf()))
                .andExpect(status().isNoContent());
    }

}