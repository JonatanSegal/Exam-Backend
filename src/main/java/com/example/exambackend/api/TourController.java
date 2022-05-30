package com.example.exambackend.api;

import com.example.exambackend.DTO.RiderResponse;
import com.example.exambackend.DTO.TeamResponse;
import com.example.exambackend.services.RiderService;
import com.example.exambackend.services.TeamService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/tour")
public class TourController {
    TeamService teamService;
    RiderService riderService;

    public TourController(TeamService teamService, RiderService riderService){
        this.riderService = riderService;
        this.teamService = teamService;
    }

    @GetMapping()
    public List<RiderResponse> getAllRiders(){
        return riderService.getAllRiders();
    }

    @GetMapping("/teams")
    public List<TeamResponse> getAllTeams(){
        return teamService.getAllTeams();
    }


}
