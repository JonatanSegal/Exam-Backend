package com.example.exambackend.services;

import com.example.exambackend.DTO.RiderRequest;
import com.example.exambackend.DTO.RiderResponse;
import com.example.exambackend.entities.Rider;
import com.example.exambackend.entities.Team;
import com.example.exambackend.mapper.Mapper;
import com.example.exambackend.repositories.RiderRepository;
import com.example.exambackend.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<RiderResponse> getRidersForTeam(String teamName){
        return RiderResponse.RiderFromEntityUsingList(riderRepository.findRiderByTeamName(teamName));
    }

    public RiderResponse shirt(String name){
        if(name.contains("YellowShirt")){
            return yellowShirt();
        }if(name.contains("GreenShirt")){
            return greenShirt();
        } if(name.contains("RedDotShirt")){
            return redDotShirt();
        }if(name.contains("WhiteShirt")){
            return whiteShirt();
        }
        return null;
    }
    public RiderResponse yellowShirt(){
        for (RiderResponse rider : getAllRiders()) {
            if (rider.getTotalTime() == fastestTime()) {
                return rider;
            }
        }
        return null;
    }
    public RiderResponse greenShirt(){
        for (RiderResponse rider : getAllRiders()) {
            if (rider.getMountainPoints() == mountainPoints()) {
                return rider;
            }
        }
        return null;
    }
    public RiderResponse redDotShirt(){
        for (RiderResponse rider : getAllRiders()) {
            if (rider.getSprintPoints() == sprintPoints() ) {
                return rider;
            }
        }
        return null;
    }


    public RiderResponse whiteShirt(){
        List<RiderResponse> validRiders = ridersUnder26();
       for (RiderResponse validRider : validRiders) {
            if (validRider.getTotalTime() == lowestTimeUnder26()) {
                return validRider;
            }
        }
        return null;
    }
    public int fastestTime(){
        List<Rider> allRiders = riderRepository.findAll();
        int startTime = 100000000;
        for(Rider allRider: allRiders){
            if(allRider.getTotalTime() < startTime){
                startTime = allRider.getTotalTime();
            }
        }
        return startTime;
    }
    public int sprintPoints(){
        List<Rider> allRiders = riderRepository.findAll();
        int base = 0;
        for(Rider allRider: allRiders){
            if(allRider.getSprintPoints() > base){
                base = allRider.getSprintPoints();
            }
        }
        return base;
    }
    public int mountainPoints(){
        List<Rider> allRiders = riderRepository.findAll();
        int base = 0;
        for(Rider allRider: allRiders){
            if(allRider.getMountainPoints() > base){
                base = allRider.getMountainPoints();
            }
        }
        return base;
    }

    public List<RiderResponse> ridersUnder26(){
        List<Rider> allRiders = riderRepository.findAll();
        List<Rider> ridersUnder26 = new ArrayList<>();
        for(int i = 0; i<allRiders.size(); i++){
            if(allRiders.get(i).getAge() < 26){
                ridersUnder26.add(allRiders.get(i));
            }
        }
        return RiderResponse.RiderFromEntityUsingList(ridersUnder26);
    }

    public int lowestTimeUnder26(){
        List<RiderResponse> validRiders = ridersUnder26();
        int timePoint = 10000000;
        for (RiderResponse validRider : validRiders) {
            if (validRider.getTotalTime() < timePoint) {
                timePoint = validRider.getTotalTime();
            }
        }
        return timePoint;
    }

    public void deleteRider(int id){
        riderRepository.deleteById(id);
    }

}
