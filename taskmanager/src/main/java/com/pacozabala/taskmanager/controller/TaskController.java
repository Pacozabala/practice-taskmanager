package com.pacozabala.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pacozabala.taskmanager.model.Task;
import com.pacozabala.taskmanager.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // read one: return 1 task by id
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }
    
    // create task: receives a JSON, saves task to repo, return saved task
    @PostMapping("/tasks")
    public ResponseEntity<Task> postTask(@Valid @RequestBody Task task) {
        return taskService.postTask(task);
    }
    
    // update task: find by id, update fields, save to repo
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id, @Valid @RequestBody Task updatedTask) {
        return taskService.putTask(id, updatedTask);
    }

    // delete task: find task by id and delete from repo
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
