package com.example.exambackend.services;

import com.example.exambackend.DTO.RiderRequest;
import com.example.exambackend.DTO.RiderResponse;
import com.example.exambackend.entities.Rider;
import com.example.exambackend.entities.Team;
import com.example.exambackend.mapper.Mapper;
import com.example.exambackend.repositories.RiderRepository;
import com.example.exambackend.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService {
    RiderRepository riderRepository;
    TeamRepository teamRepository;
    TeamService teamService;
   // Mapper mapper;


    public RiderService(RiderRepository riderRepository, TeamRepository teamRepository,
                        TeamService teamService) {
        this.riderRepository = riderRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    public List<RiderResponse> getAllRiders(){
        List<Rider> riderEntities = riderRepository.findAll();
        return  RiderResponse.RiderFromEntityUsingList(riderEntities);
    }

    public RiderResponse addRider(RiderRequest body) throws Exception {
        Rider newRider = riderRepository.save(new Rider(body));
        Team team = teamService.getTeam(body.getTeam());
        team.addRider(newRider);
        teamRepository.save(team);
        return new RiderResponse(newRider,true);
    }

    public RiderResponse getRider(int id) throws Exception{
        Rider riderToFind = riderRepository.findById(id).orElseThrow(()-> new Exception("No rider with that name"));
        return new RiderResponse(riderToFind,false);
    }

    public RiderResponse editRider(RiderRequest body, int id) throws Exception{
        Rider riderToEdit = riderRepository.findById(id).orElseThrow(()-> new Exception("Could not find rider"));
        riderToEdit.setName(body.getName());
        riderToEdit.setAge(body.getAge());
        riderToEdit.setCountry(body.getCountry());
        riderToEdit.setTeam(teamService.getTeam(body.getTeam()));
        riderToEdit.setMountainPoints(body.getMountainPoints());
        riderToEdit.setSprintPoints(body.getSprintPoints());
        riderToEdit.setTotalTime(body.getTotalTime());
        riderRepository.save(riderToEdit);
        return new RiderResponse(riderToEdit,false);
    }

    public void deleteRider(int id){
        riderRepository.deleteById(id);
    }

}
