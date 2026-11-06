package com.example.taskboard.Service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskboard.DTO.BoardRequestDTO;
import com.example.taskboard.DTO.BoardResponseDTO;
import com.example.taskboard.Model.Board;
import com.example.taskboard.Model.User;
import com.example.taskboard.Repository.BoardRepository;
import com.example.taskboard.Exception.ResourceNotFoundException;
import com.example.taskboard.Mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
/**
 * Couche Service pour la gestion des tableaux (Board).
 *
 * Utilise BoardMapper pour convertir les DTOs vers les entites JPA et inversement.
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final BoardMapper boardMapper;

    // CREATE

    public BoardResponseDTO createBoard(Long ownerId, BoardRequestDTO requestDTO) {
        User owner = userService.getUserEntityById(ownerId);
        Board board = boardMapper.toEntity(requestDTO);
        board.setOwner(owner);

        Board savedBoard = boardRepository.save(board);
        return boardMapper.toResponseDTO(savedBoard);
    }

    // READ

    public List<BoardResponseDTO> getAllBoards() {
        return boardRepository.findAll()
                .stream()
                .map(boardMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<BoardResponseDTO> getBoardsByOwner(Long ownerId) {
        userService.getUserEntityById(ownerId);
        return boardRepository.findByOwnerId(ownerId)
                .stream()
                .map(boardMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public BoardResponseDTO getBoardById(Long id) {
        Board board = getBoardEntityById(id);
        return boardMapper.toResponseDTO(board);
    }

    public Board getBoardEntityById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tableau introuvable avec id=" + id));
    }

    // UPDATE

    public BoardResponseDTO updateBoard(Long id, BoardRequestDTO requestDTO) {
        Board existingBoard = getBoardEntityById(id);
        existingBoard.setTitle(requestDTO.getTitle());

        Board updatedBoard = boardRepository.save(existingBoard);
        return boardMapper.toResponseDTO(updatedBoard);
    }

    // DELETE

    public void deleteBoard(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Tableau introuvable avec id=" + id);
        }
        boardRepository.deleteById(id);
    }
}
