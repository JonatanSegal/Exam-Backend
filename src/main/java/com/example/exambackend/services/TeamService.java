package com.example.exambackend.services;

import com.example.exambackend.DTO.TeamResponse;
import com.example.exambackend.entities.Team;
import com.example.exambackend.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService  {
    TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public List<TeamResponse> getAllTeams(){
        List<Team> teamEntities = teamRepository.findAll();
        return TeamResponse.TeamFromEntity(teamEntities);
    }

    public Team getTeam(int teamName) throws Exception{
        Team teamToFind =  teamRepository.findById(teamName).orElseThrow(()-> new Exception("No team with that name"));
        return teamToFind;
    }
}
