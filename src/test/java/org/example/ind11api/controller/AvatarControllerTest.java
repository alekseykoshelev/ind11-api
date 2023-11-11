package org.example.ind11api.controller;

import org.example.ind11api.model.Avatar;
import org.example.ind11api.repository.AvatarRepository;
import org.example.ind11api.repository.FacultyRepository;
import org.example.ind11api.repository.StudentRepository;
import org.example.ind11api.service.AvatarService;
import org.example.ind11api.service.FacultyService;
import org.example.ind11api.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class AvatarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;
    @MockBean
    AvatarRepository avatarRepository;
    @MockBean
    FacultyRepository facultyRepository;

    @SpyBean
    StudentService studentService;
    @SpyBean
    AvatarService avatarService;
    @SpyBean
    FacultyService facultyService;

    @InjectMocks
    AvatarController avatarController;

    @Test
    void testGetPhoto() throws Exception {
        var a = new Avatar();
        a.setMediaType("image/png");
        a.setData(new byte[]{1, 2, 3});

//        when(avatarRepository.findByStudentId(anyLong())).thenReturn(Optional.of(a));
        when(avatarService.find(anyLong())).thenReturn(a);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/avatar/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().bytes(new byte[]{1, 2, 3}));

    }
}