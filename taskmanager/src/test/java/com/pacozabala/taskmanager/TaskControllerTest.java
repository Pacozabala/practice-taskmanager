package com.pacozabala.taskmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.pacozabala.taskmanager.model.Task;
import com.pacozabala.taskmanager.repository.TaskRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();

        Task task1 = new Task();
        task1.setTitle("Finish report");
        task1.setDescription("Complete the montly report");
        task1.setCompleted(false);
        task1.setDueDate(LocalDate.of(2026,8,20));

        Task task2 = new Task();
        task2.setTitle("Buy groceries");
        task2.setDescription("Buy food");
        task2.setCompleted(true);
        task2.setDueDate(LocalDate.of(2026, 8, 18));

        Task task3 = new Task();
        task3.setTitle("Write report");
        task3.setDescription("Write project report");
        task3.setCompleted(false);
        task3.setDueDate(LocalDate.of(2026, 8, 25));

        taskRepository.saveAll(List.of(task1, task2, task3));
    }

    @Test
    void shouldFilterCompletedTasks() throws Exception {
        mockMvc.perform(get("/tasks")
                .param("completed", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Buy groceries"));
    }

    @Test
    void shouldSearchTasksByTitle() throws Exception {
        mockMvc.perform(get("/tasks/search")
                .param("title", "report"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldSortByDueDate() throws Exception {
        mockMvc.perform(get("/tasks/sort/due-date"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Buy groceries"))
                .andExpect(jsonPath("$[1].title").value("Finish report"))
                .andExpect(jsonPath("$[2].title").value("Write report"));
    }
}
