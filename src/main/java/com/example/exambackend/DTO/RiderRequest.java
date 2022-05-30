package com.example.exambackend.DTO;

import lombok.Getter;

@Getter
public class RiderRequest {

    private String name;
    private int age;
    private String country;
    private int team;
    private int mountainPoints;
    private int sprintPoints;
    private int totalTime;

}
