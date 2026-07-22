package com.pacozabala.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pacozabala.taskmanager.model.Task;
import com.pacozabala.taskmanager.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




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
    
    // read all: return all tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks;
    }

    // read one: return 1 task by id
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Optional<Task>> getTask(@RequestParam Long id) {
        //TODO: Implement get one method

        Optional<Task> foundTask = taskRepository.findById(id);

        if (foundTask.isPresent()) {
            return ResponseEntity.ok(foundTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // create task: receives a JSON, saves task to repo, return saved task
    @PostMapping("/tasks/create")
    public Task createTask(@RequestBody Task task) {

        Task savedTask = taskRepository.save(task);
        
        return savedTask;
    }
    
    
}
