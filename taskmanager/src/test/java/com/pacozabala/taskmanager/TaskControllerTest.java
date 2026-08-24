package com.pacozabala.taskmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pacozabala.taskmanager.dto.TaskResponse;
import com.pacozabala.taskmanager.model.Task;
import com.pacozabala.taskmanager.repository.TaskRepository;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskRepository taskRepository;

    // =========================================================
    // CRUD TESTS
    // =========================================================

    @Test
    void createTask() throws Exception {
        String request = """
                {
                    "title": "Test task",
                    "description": "Testing task creation",
                    "dueDate": "2026-10-30"
                }
            """;

        mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Test task"));
    }

    @Test
    void updateTask() throws Exception {
        String request = """
            {
                "title": "Original task",
                "description": "Original description",
                "dueDate": "2026-10-30"
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
            "dueDate": "2026-11-01"
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
                "dueDate": "2026-10-30"
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
        .andExpect(status().isNoContent());
    }

    @Test
    void taskNotFound() throws Exception {
        mockMvc.perform(get("/tasks/999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.message").exists());;
    }

    // =========================================================
    // VALIDATION TESTS
    // =========================================================

    @Test
    void createTaskNoTitle() throws Exception {
        String request = """
            {
                "title": "",
                "description": "No title.",
                "dueDate": "2026-10-30"
            }
        """;

        mockMvc.perform(post("/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andExpect(status().isBadRequest());
    }

    @Test
    void createTaskLongTitle() throws Exception {
        String request = """
            {
                "title": "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm",
                "description": "Long title.",
                "dueDate": "2026-08-30"
            }
        """;

        mockMvc.perform(post("/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andExpect(status().isBadRequest());
    }

    @Test
    void createTaskPastDate() throws Exception {
        String request = """
            {
                "title": "Invalid date",
                "description": "Task with invalid date.",
                "dueDate": "2026-07-30"
            }
        """;

        mockMvc.perform(post("/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andExpect(status().isBadRequest());
    }

    // =========================================================
    // FILTERING / SEARCHING / SORTING TESTS
    // =========================================================

    private void createTestTasks() {
        taskRepository.deleteAll();

        Task task1 = new Task();
        task1.setTitle("Finish report");
        task1.setDescription("Complete the montly report");
        task1.setCompleted(false);
        task1.setDueDate(LocalDate.of(2026,8,20));
        task1.setCreatedAt(LocalDateTime.of(2026, 8, 10, 10, 0));

        Task task2 = new Task();
        task2.setTitle("Buy groceries");
        task2.setDescription("Buy food");
        task2.setCompleted(true);
        task2.setDueDate(LocalDate.of(2026, 8, 18));
        task2.setCreatedAt(LocalDateTime.of(2026, 8, 12, 10, 0));

        Task task3 = new Task();
        task3.setTitle("Write report");
        task3.setDescription("Write project report");
        task3.setCompleted(false);
        task3.setDueDate(LocalDate.of(2026, 8, 25));
        task3.setCreatedAt(LocalDateTime.of(2026, 8, 15, 10, 0));

        taskRepository.saveAll(List.of(task1, task2, task3));
    }

    @Test
    void shouldFilterCompletedTasks() throws Exception {
        createTestTasks();

        mockMvc.perform(get("/tasks")
                .param("completed", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Buy groceries"));
    }

    @Test
    void shouldSearchTasksByTitle() throws Exception {
        createTestTasks();

        mockMvc.perform(get("/tasks/search")
                .param("title", "report"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldSortByDueDate() throws Exception {
        createTestTasks();

        mockMvc.perform(get("/tasks/sort/due-date"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Buy groceries"))
                .andExpect(jsonPath("$[1].title").value("Finish report"))
                .andExpect(jsonPath("$[2].title").value("Write report"));
    }

    @Test
    void shouldSortByCreationDate() throws Exception {
        createTestTasks();

        mockMvc.perform(get("/tasks/sort/created"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].title").value("Write report"))
                .andExpect(jsonPath("$[1].title").value("Buy groceries"))
                .andExpect(jsonPath("$[2].title").value("Finish report"));
    }

}
