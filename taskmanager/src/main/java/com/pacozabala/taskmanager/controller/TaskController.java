package com.pacozabala.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pacozabala.taskmanager.dto.TaskRequest;
import com.pacozabala.taskmanager.dto.TaskResponse;
import com.pacozabala.taskmanager.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping("/tasks")
    public List<TaskResponse> getAllTasks(@RequestParam(required=false) Optional<Boolean> completed) {
        if(completed.isPresent()) {
            return taskService.getTasksByCompleted(completed.get());
        }

        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/search")
    public List<TaskResponse> searchTasks(@RequestParam String title) {
        return taskService.searchTasks(title);
    }

    @GetMapping("/tasks/sort/due-date")
    public List<TaskResponse> getTasksSortedByDueDate() {
        return taskService.getTasksSortedByDueDate();
    }
    

    // read one: return 1 task by id
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }
    
    // create task: receives a JSON, saves task to repo, return saved task
    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> postTask(@Valid @RequestBody TaskRequest task) {
        return ResponseEntity.ok(taskService.postTask(task));
    }
    
    // update task: find by id, update fields, save to repo
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> putTask(@PathVariable Long id, @Valid @RequestBody TaskRequest updatedTask) {
        return ResponseEntity.ok(taskService.putTask(id, updatedTask));
    }

    // delete task: find task by id and delete from repo
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
