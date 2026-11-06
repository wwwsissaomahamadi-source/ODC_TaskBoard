package com.example.taskboard.Mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import com.example.taskboard.DTO.BoardRequestDTO;
import com.example.taskboard.DTO.BoardResponseDTO;
import com.example.taskboard.Model.Board;

/**
 * Mapper pour transformer les objets Board en DTOs et inversement.
 */
@Component
public class BoardMapper {

    /**
     * Convertit un DTO de creation en entite Board.
     */
    public Board toEntity(BoardRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setCreatedAt(LocalDateTime.now());
        return board;
    }

    /**
     * Convertit une entite Board en DTO de reponse.
     */
    public BoardResponseDTO toResponseDTO(Board entity) {
        if (entity == null) {
            return null;
        }
        return BoardResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .ownerId(entity.getOwner() != null ? entity.getOwner().getId() : null)
                .ownerName(entity.getOwner() != null ? entity.getOwner().getName() : null)
                .build();
    }
}

