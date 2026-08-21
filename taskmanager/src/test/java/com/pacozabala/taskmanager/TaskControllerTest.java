package com.pacozabala.taskmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

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

    @Test
    void getTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk());
    }

    @Test
    void updateTask() throws Exception {
        String createRequest = """
            {
                "title": "Original task",
                "description": "Original description",
                "dueDate": "2026-08-30"
            }
            """;

        String response = mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        // extract ID from response

        String updateRequest = """
        {
            "title": "Updated task",
            "description": "Updated description",
            "dueDate": "2026-09-01"
        }
        """;

        // PUT /tasks/{id}
    }

    @Test
    void deleteTask() throws Exception {
        String createRequest = """
            {
                "title": "Original task",
                "description": "Original description",
                "dueDate": "2026-08-30"
            }
            """;
    }

    @Test
    void taskNotFound() throws Exception {
        mockMvc.perform(get("/tasks/999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.message").exists());;
    }

}
