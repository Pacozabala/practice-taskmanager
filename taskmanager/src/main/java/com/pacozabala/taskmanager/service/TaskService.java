package com.pacozabala.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pacozabala.taskmanager.dto.TaskRequest;
import com.pacozabala.taskmanager.dto.TaskResponse;
import com.pacozabala.taskmanager.exception.TaskNotFoundException;
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
    public Task getTask(Long id) {
        Task foundTask = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));

        return foundTask;
    }

    // create task: receives a JSON, saves task to repo, return saved task
    public Task postTask(Task task) {
        Task newTask = taskRepository.save(task);
        
        return newTask;
    }

    // update task: find by id, update fields, save to repo
    public Task putTask(Long id, Task taskToUpdate) {
            
        Task foundTask = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));

        foundTask.setTitle(taskToUpdate.getTitle());
        foundTask.setDescription(taskToUpdate.getDescription());
        foundTask.setDueDate(taskToUpdate.getDueDate());
        foundTask.setCompleted(taskToUpdate.isCompleted());

        Task savedTask = taskRepository.save(foundTask);

        return savedTask;
    }

    // delete task: find task by id and delete from repo
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));

        taskRepository.delete(task);

    }

    private Task toEntity(TaskRequest request) {
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());

        return task;
    }

    private TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.isCompleted());
        response.setDueDate(task.getDueDate());
        response.setCreatedAt(task.getCreatedAt());

        return response;
    }

}
