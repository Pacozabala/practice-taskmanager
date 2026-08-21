package com.pacozabala.taskmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pacozabala.taskmanager.dto.TaskResponse;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTask() throws Exception {
        String request = """
                {
                    "title": "Test task",
                    "description": "Testing task creation",
                    "dueDate": "2026-08-30"
                }
            """;

        mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Test task"));
    }

    // @Test
    // void getTasks() throws Exception {
    //     mockMvc.perform(get("/tasks"))
    //         .andExpect(status().isOk());
    // }

    @Test
    void updateTask() throws Exception {
        String request = """
            {
                "title": "Original task",
                "description": "Original description",
                "dueDate": "2026-08-30"
            }
            """;

        String response = mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        // extract ID from response
        TaskResponse createdTask = objectMapper.readValue(response, TaskResponse.class);
        
        Long id = createdTask.getId();

        String updateRequest = """
        {
            "title": "Updated task",
            "description": "Updated description",
            "dueDate": "2026-09-01"
        }
        """;

        // PUT /tasks/{id}
        mockMvc.perform(put("/tasks/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(updateRequest))
            .andExpect(status().isOk());
    }

    @Test
    void deleteTask() throws Exception {
        String request = """
            {
                "title": "Original task",
                "description": "Original description",
                "dueDate": "2026-08-30"
            }
            """;

        String response = mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        // extract ID from response
        TaskResponse createdTask = objectMapper.readValue(response, TaskResponse.class);
        
        Long id = createdTask.getId();

        mockMvc.perform(delete("/tasks/{id}", id))
        .andExpect(status().isOk());
    }

    @Test
    void taskNotFound() throws Exception {
        mockMvc.perform(get("/tasks/999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.message").exists());;
    }

}
