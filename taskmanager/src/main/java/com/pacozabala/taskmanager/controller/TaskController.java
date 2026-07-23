package com.pacozabala.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pacozabala.taskmanager.model.Task;
import com.pacozabala.taskmanager.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;





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
        return taskRepository.findAll();
    }

    // read one: return 1 task by id
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task foundTask = taskOptional.get();
            return ResponseEntity.ok(foundTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // create task: receives a JSON, saves task to repo, return saved task
    @PostMapping("/tasks")
    public ResponseEntity<Task> postTask(@RequestBody Task task) {

        Task newTask = taskRepository.save(task);
        
        return ResponseEntity.ok(newTask);
    }
    
    // update task: find by id, update fields, save to repo
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        
        Optional<Task> taskOptional = taskRepository.findById(id);
        
        if (taskOptional.isPresent()) {
            Task foundTask = taskOptional.get();

            foundTask.setTitle(updatedTask.getTitle());
            foundTask.setDescription(updatedTask.getDescription());
            foundTask.setDueDate(updatedTask.getDueDate());
            foundTask.setCompleted(updatedTask.isCompleted());

            Task savedTask = taskRepository.save(foundTask);

            return ResponseEntity.ok(savedTask);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete task: find task by id and delete from repo
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
