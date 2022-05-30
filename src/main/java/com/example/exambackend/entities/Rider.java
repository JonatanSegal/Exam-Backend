package com.example.exambackend.entities;

import com.example.exambackend.DTO.RiderRequest;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    //Many riders for one team
    @ManyToOne()
    private Team team;

    private int mountainPoints;
    private int sprintPoints;
    private int totalTime;
    private String country;

    public Rider(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public Rider(RiderRequest request){
        this.name = request.getName();
        this.age = request.getAge();
        this.country = request.getCountry();
    }
}
