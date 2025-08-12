package com.example.homework31.controller;

import com.example.homework31.service.CommentEntityService;
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

@WebMvcTest(CommentController.class)
@AutoConfigureMockMvc
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CommentEntityService commentEntityService;

    @Test
    @DisplayName("GET /comments — запрещено без авторизации")
    void getAllComments_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/comments"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /comment/id/{id} — запрещено без авторизации")
    void getCommentById_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/comment/id/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /comment/comment/{comment} — запрещено без авторизации")
    void getCommentByParam_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/comment/comment/someText"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /comment — запрещено без авторизации")
    void createComment_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/comment")
                        .param("comment", "Test comment")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("DELETE /comment/{id} — запрещено без авторизации")
    void deleteComment_unauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(delete("/comment/1").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user")
    @DisplayName("GET /comments — доступно авторизованным")
    void getAllComments_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("GET /comment/id/{id} — доступно авторизованным")
    void getCommentById_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/comment/id/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("GET /comment/comment/{comment} — доступно авторизованным")
    void getCommentByParam_authenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/comment/comment/someText"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("POST /comment — доступно авторизованным")
    void createComment_authenticated_shouldReturnCreated() throws Exception {
        mockMvc.perform(post("/comment")
                        .param("comment", "Test comment")
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin")
    @DisplayName("DELETE /comment/{id} — доступно авторизованным")
    void deleteComment_authenticated_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/comment/1").with(csrf()))
                .andExpect(status().isNoContent());
    }
}