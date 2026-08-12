package com.pacozabala.taskmanager.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {
    @NotBlank(message = "Title cannot be blank.")
    @Size(max=100, message="Title cannot exceed 100 characters")
    private String title;
        
    @Size(max=500, message="Description cannot exceed 500 characters.")
    private String description;

    @FutureOrPresent(message = "Due date cannot be in the past.")
    private LocalDate dueDate;

    private boolean completed;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    
}
