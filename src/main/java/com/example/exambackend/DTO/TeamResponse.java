package com.example.exambackend.DTO;

import com.example.exambackend.entities.Rider;
import com.example.exambackend.entities.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TeamResponse {

    private String name;
    private List<RiderResponse> riderResponses;

    public TeamResponse(Team team, boolean includeAll){
        this.name = team.getName();
        this.riderResponses = RiderResponse.RiderFromEntity(team.getRiders());
    }

    public static List<TeamResponse> TeamFromEntity(List<Team> teams){
        return teams.stream().map(team -> new TeamResponse(team,false)).collect(Collectors.toList());
    }

}
