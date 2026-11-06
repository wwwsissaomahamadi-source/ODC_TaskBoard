package com.example.taskboard.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskboard.DTO.TaskRequestDTO;
import com.example.taskboard.DTO.TaskResponseDTO;
import com.example.taskboard.Model.Board;
import com.example.taskboard.Model.Task;
import com.example.taskboard.Model.TaskStatus;
import com.example.taskboard.Repository.TaskRepository;
import com.example.taskboard.Exception.ResourceNotFoundException;
import com.example.taskboard.Mapper.TaskMapper;

import lombok.RequiredArgsConstructor;

/**
 * Couche Service pour la gestion des taches (Task).
 *
 * Utilise TaskMapper pour convertir les DTOs vers les entites JPA et inversement.
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final BoardService boardService;
    private final TaskMapper taskMapper;

    // CREATE

    public TaskResponseDTO createTask(Long boardId, TaskRequestDTO requestDTO) {
        Board board = boardService.getBoardEntityById(boardId);

        Task task = taskMapper.toEntity(requestDTO);
        if (task.getStatut() == null) {
            task.setStatut(TaskStatus.A_FAIRE);
        }
        task.setBoard(board);

        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponseDTO(savedTask);
    }

    // READ

    public List<TaskResponseDTO> getTasksByBoard(Long boardId) {
        boardService.getBoardEntityById(boardId);
        return taskRepository.findByBoardId(boardId)
                .stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = getTaskEntityById(id);
        return taskMapper.toResponseDTO(task);
    }

    public Task getTaskEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tache introuvable avec id=" + id));
    }

    // UPDATE

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO) {
        Task existingTask = getTaskEntityById(id);

        existingTask.setTitle(requestDTO.getTitle());
        existingTask.setDescription(requestDTO.getDescription());
        if (requestDTO.getStatut() != null) {
            existingTask.setStatut(requestDTO.getStatut());
        }

        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.toResponseDTO(updatedTask);
    }

    // DELETE

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Tache introuvable avec id=" + id);
        }
        taskRepository.deleteById(id);
    }
}
