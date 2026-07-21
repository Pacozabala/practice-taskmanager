package com.pacozabala.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pacozabala.taskmanager.model.Task;
import com.pacozabala.taskmanager.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks/test")
    public Task repositoryTest() {
        Task task = new Task();

        task.setTitle("test task");
        task.setDescription("This is a task to test the repository.");
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());
        task.setDueDate(LocalDate.now().plusDays(7));
        
        Task savedTask = taskRepository.save(task);

        return savedTask;
    }
    
    @GetMapping("/tasks")
    public List<Task> getTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks;
    }
}
