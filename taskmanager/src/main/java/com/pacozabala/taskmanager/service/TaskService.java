package com.pacozabala.taskmanager.service;

import java.util.ArrayList;
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
    public List<TaskResponse> getAllTasks() {
        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : taskRepository.findAll()) {
            responses.add(toResponse(task));
        }

        return responses;
    }

    // get tasks based on completed status
    public List<TaskResponse> getTasksByCompleted(Boolean completed) {
        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : taskRepository.findByCompleted(completed)) {
            responses.add(toResponse(task));
        }

        return responses;
    }

    // get tasks based on title
    public List<TaskResponse> searchTasks(String title) {
        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : taskRepository.findByTitleContainingIgnoreCase(title)) {
            responses.add(toResponse(task));
        }

        return responses;
    }

    // get all tasks sorted by due date ASC
    public List<TaskResponse> getTasksSortedByDueDate() {
        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : taskRepository.findAllByOrderByDueDateAsc()) {
            responses.add(toResponse(task));
        }

        return responses;
    }

    // get all tasks sorted by creation date DESC
    public List<TaskResponse> getTasksSortedByCreatedDate() {
        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : taskRepository.findAllByOrderByCreatedAtDesc()) {
            responses.add(toResponse(task));
        }

        return responses;
    }

    // read one: return 1 task by id
    public TaskResponse getTask(Long id) {
        Task foundTask = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));

        return toResponse(foundTask);
    }

    // create task: receives a JSON, saves task to repo, return saved task
    public TaskResponse postTask(TaskRequest request) {
        Task task = toEntity(request);
        Task newTask = taskRepository.save(task);
        
        return toResponse(newTask);
    }

    // update task: find by id, update fields, save to repo
    public TaskResponse putTask(Long id, TaskRequest request) {
            
        Task foundTask = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));

        foundTask.setTitle(request.getTitle());
        foundTask.setDescription(request.getDescription());
        foundTask.setDueDate(request.getDueDate());
        foundTask.setCompleted(request.isCompleted());

        Task savedTask = taskRepository.save(foundTask);

        return toResponse(savedTask);
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
