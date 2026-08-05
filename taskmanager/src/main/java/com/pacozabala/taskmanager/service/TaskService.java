package com.pacozabala.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pacozabala.taskmanager.model.Task;
import com.pacozabala.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // read all: return all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // read one: return 1 task by id
    public ResponseEntity<Task> getTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task foundTask = taskOptional.get();
            return ResponseEntity.ok(foundTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // create task: receives a JSON, saves task to repo, return saved task
    public ResponseEntity<Task> postTask(Task task) {
        Task newTask = taskRepository.save(task);
        
        return ResponseEntity.ok(newTask);
    }

    // update task: find by id, update fields, save to repo
    public ResponseEntity<Task> putTask(Long id, Task updatedTask) {
            
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
    public ResponseEntity<Void> deleteTask(Long id) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
