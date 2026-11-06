package com.example.taskboard.DTO;


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

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    
}
