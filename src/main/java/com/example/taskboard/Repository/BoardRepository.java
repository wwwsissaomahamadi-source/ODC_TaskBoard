package com.example.taskboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskboard.Model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByOwnerId(Long ownerId);


}