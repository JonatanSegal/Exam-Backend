package com.example.exambackend.api;

import com.example.exambackend.DTO.RiderRequest;
import com.example.exambackend.DTO.RiderResponse;
import com.example.exambackend.DTO.TeamResponse;
import com.example.exambackend.services.RiderService;
import com.example.exambackend.services.TeamService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public RiderResponse addRider(@RequestBody RiderRequest body) throws Exception {
        return riderService.addRider(body);
    }
    @PatchMapping("/{id}")
    public RiderResponse updateRider(@RequestBody RiderRequest body, @PathVariable int id) throws Exception {
        return riderService.editRider(body,id);
    }



    @GetMapping("/teams")
    public List<TeamResponse> getAllTeams(){
        return teamService.getAllTeams();
    }


}
