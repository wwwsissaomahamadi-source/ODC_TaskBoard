package com.example.taskboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskboard.Model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

   
    List<Task> findByBoardId(Long boardId);
}