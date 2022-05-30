package com.example.exambackend.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    //A team has many riders
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Set<Rider> ryttere = new HashSet<>();

}
