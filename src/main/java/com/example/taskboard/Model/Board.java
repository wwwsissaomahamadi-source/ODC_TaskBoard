package com.example.taskboard.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "boards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Board{

    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String  title;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "owner_id", nullable = false)
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task>  tasks = new ArrayList<>();




}

/*public class Board{
    private long id;
    private String title;
    private LocalDateTime createdAt;
    private User owner;

    public Board(long id, String title, LocalDateTime createdAt, User owner) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.owner = owner;
    }
    // les getters
    public long getId(){
        return id;
    }
    public String getTitle(){
        return title;

    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public User getOwner(){
        return owner;
    }

    //Les setters

    public void setId(long id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    public void setOwner(User owner){
        this.owner = owner;
    }



} */

