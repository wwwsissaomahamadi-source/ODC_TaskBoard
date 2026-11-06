package com.example.taskboard.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Task{


    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus statut = TaskStatus.A_FAIRE; //Statut initial impose

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public Object getBoard() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}

/*public class Task {
    private long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Board board;

    public Task(long id, String title, String description, TaskStatus status, Board board){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.board = board;
    }
    //Les getters
    public long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }
    public TaskStatus  getStatus(){
        return status;
    }
    public Board getBoard(){
        return board;
    }

    //Les setters

    public void setId(long id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }
    
    public void setStatus(TaskStatus status){
        this.status = status;

    }
    public void setBoard(Board board){
        this.board = board;
    }
    
}*/
