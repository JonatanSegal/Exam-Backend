package com.example.exambackend.config;

import com.example.exambackend.entities.Rider;
import com.example.exambackend.entities.Team;
import com.example.exambackend.repositories.RiderRepository;
import com.example.exambackend.repositories.TeamRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Profile("!test")
public class MakeTestData implements ApplicationRunner {
    RiderRepository riderRepository;
    TeamRepository teamRepository;

    public MakeTestData(RiderRepository riderRepository, TeamRepository teamRepository){
        this.riderRepository = riderRepository;
        this.teamRepository = teamRepository;
    }

    public void makeTeamsWithRiders(){
        Team team1 = new Team("Team DSM");
        Team team2 = new Team("Confidis");
        Team team3 = new Team("Groupama - FDJ");

        Rider rider1 = new Rider("Soren Andersen",24,100,200,1200, "Denmark");
        Rider rider2 = new Rider("Cees Bol", 27,150,300,1330, "Netherlands");
        Rider rider3 = new Rider("Olivier Le Gac", 28,75,200,945, "France");
        Rider rider4 = new Rider("Quinn Simmons", 25,85,150,1350,"USA");
        Rider rider5 = new Rider("Ion Izagirre", 23,120,195,1245 , "Spain");
        Rider rider6 = new Rider("Adam yates", 26,65,220,1410,"UK");
        Rider rider7 = new Rider("Niklas Arndt", 27,110,140,1230,"Germany");
        Rider rider8 = new Rider("Jakob Fuglsang", 24,115,180,1420,"Denmark");
        Rider rider9 = new Rider("Fabian Doubey", 25,85,135,1620,"France");
        Rider rider10 = new Rider("Nils Politt", 26,130,230,1140, "Germany");

        team1.addRiders(Set.of(rider1,rider8,rider7,rider10));
        team2.addRiders(Set.of(rider2,rider3,rider9));
        team3.addRiders(Set.of(rider4,rider5,rider6));

        teamRepository.saveAll(List.of(team1,team2,team3));

    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        riderRepository.deleteAll();
        teamRepository.deleteAll();
        makeTeamsWithRiders();
    }

}
