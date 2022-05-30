package com.example.exambackend.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    //Many riders for one team
    @ManyToOne
    private Team team;

    private int mountainPoints;
    private int sprintPoints;
    private int totalTime;
}
