package com.example.taskboard.Mapper;

import org.springframework.stereotype.Component;
import com.example.taskboard.DTO.TaskRequestDTO;
import com.example.taskboard.DTO.TaskResponseDTO;
import com.example.taskboard.DTO.TaskUpdateRequestDTO;
import com.example.taskboard.Model.Task;
import com.example.taskboard.Model.TaskStatus;

/**
 * Mapper pour transformer les objets Task en DTOs et inversement.
 */
@Component
public class TaskMapper {

    /**
     * Convertit un DTO de creation en entite Task (statut initial A_FAIRE).
     */
    public Task toEntity(TaskRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatut(TaskStatus.A_FAIRE);
        return task;
    }

    /**
     * Met a jour une entite existante a partir d'un DTO de mise a jour.
     */
    public void updateEntityFromDTO(Task entity, TaskUpdateRequestDTO dto) {
        if (entity == null || dto == null) {
            return;
        }
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatut(dto.getStatut());
    }

    /**
     * Convertit une entite Task en DTO de reponse.
     */
    public TaskResponseDTO toResponseDTO(Task entity) {
        if (entity == null) {
            return null;
        }
        return TaskResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .statut(entity.getStatut())
                .boardId(entity.getBoard() != null ? ((TaskResponseDTO) entity.getBoard()).getId() : null)
                .build();
    }
}

