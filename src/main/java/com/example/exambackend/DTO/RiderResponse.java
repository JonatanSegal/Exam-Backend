package com.example.exambackend.DTO;

import com.example.exambackend.entities.Rider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RiderResponse {

    private int id;

    private String name;
    private int age;
    private String country;
    private String team;


    private int mountainPoints;
    private int sprintPoints;
    private int totalTime;

    public RiderResponse(Rider rider, boolean includeAll) {
        this.id = rider.getId();
        this.name = rider.getName();
        this.age = rider.getAge();
        this.country = rider.getCountry();
        this.team = rider.getTeam().getName();
        this.mountainPoints = rider.getMountainPoints();
        this.sprintPoints = rider.getSprintPoints();
        this.totalTime = rider.getTotalTime();
    }

    public static List<RiderResponse> RiderFromEntity(Set<Rider> riders){
        return riders.stream().map(rider -> new RiderResponse(rider,false)).collect(Collectors.toList());
    }
    public static List<RiderResponse> RiderFromEntityUsingList(List<Rider> riders){
        return riders.stream().map(rider -> new RiderResponse(rider,false)).collect(Collectors.toList());
    }
}
