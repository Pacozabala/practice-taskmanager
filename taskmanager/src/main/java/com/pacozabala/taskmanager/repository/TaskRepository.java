package com.pacozabala.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pacozabala.taskmanager.model.Task;

// <Task, Long>: repository manages Task entities, entity ID type is Long
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findAllByOrderByDueDateAsc();

    List<Task> findAllByOrderByCreatedAtDesc();
    
}
