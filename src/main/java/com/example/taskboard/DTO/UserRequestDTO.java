package com.example.taskboard.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 


public class UserRequestDTO {

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom d'utilisateur doit contenir entre 2 et 50 caractères")
    private String name;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, max = 50, message = "Le mot de passe doit contenir entre 8 et 50 caractères")
    private String password;
    
    
}
