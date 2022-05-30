package com.example.exambackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    //A team has many riders
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST ,CascadeType.MERGE})
    private Set<Rider> riders = new HashSet<>();

    public Team(String name){
        this.name = name;
    }

    public void addRider(Rider rider){
        riders.add(rider);
    }

    public void addRiders(Set<Rider> riders){
        this.riders.addAll(riders);
        for(Rider rider : riders){
            rider.setTeam(this);
        }
    }
}
