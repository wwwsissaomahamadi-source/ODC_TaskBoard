package com.example.taskboard.Controller;

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

import com.example.taskboard.DTO.UserRequestDTO;
import com.example.taskboard.DTO.UserResponseDTO;
import com.example.taskboard.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Couche Controller : expose l'API REST pour les utilisateurs.
 *
 * Regles d'or du Controller :
 * - Il ne contient AUCUNE logique metier.
 * - Il recoit la requete HTTP, delege au Service, retourne la reponse HTTP.
 * - C'est tout.
 *
 * Endpoints disponibles :
 *   POST   /api/users       -> creer un utilisateur
 *   GET    /api/users       -> lister tous les utilisateurs
 *   GET    /api/users/{id}  -> recuperer un utilisateur par id
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    // CREATE
     
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO requestDTO) {

        UserResponseDTO created = userService.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
   
    // READ
     
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    // UPDATE
     
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO requestDTO) {

        return ResponseEntity.ok(userService.updateUser(id, requestDTO));
    }
   
    // DELETE
     
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}