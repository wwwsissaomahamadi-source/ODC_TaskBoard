package com.example.taskboard.Controller;

// List est le type de résultat des opérations qui renvoient plusieurs tâches.

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskboard.DTO.TaskRequestDTO;
import com.example.taskboard.DTO.TaskResponseDTO;
import com.example.taskboard.Service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Couche Controller : expose l'API REST pour les taches (Task).
 *
 * Endpoints disponibles :
 *   POST   /api/boards/{boardId}/tasks           -> creer une tache
 *   GET    /api/boards/{boardId}/tasks           -> lister les taches d'un tableau
 *   GET    /api/boards/{boardId}/tasks/{taskId}  -> recuperer une tache par id
 *   PUT    /api/boards/{boardId}/tasks/{taskId}  -> mettre a jour une tache
 *   DELETE /api/boards/{boardId}/tasks/{taskId}  -> supprimer une tache
 */
@RestController
@RequestMapping("/api/boards/{boardId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // CREATE

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @PathVariable Long boardId,
            @Valid @RequestBody TaskRequestDTO requestDTO) {

        TaskResponseDTO created = taskService.createTask(boardId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getTasks(@PathVariable Long boardId) {
        return ResponseEntity.ok(taskService.getTasksByBoard(boardId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> getTaskById(
            @PathVariable Long boardId,
            @PathVariable Long taskId) {

        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    // UPDATE

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long boardId,
            @PathVariable Long taskId,
            @Valid @RequestBody TaskRequestDTO requestDTO) {

        return ResponseEntity.ok(taskService.updateTask(taskId, requestDTO));
    }

    // DELETE

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long boardId,
            @PathVariable Long taskId) {

        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
