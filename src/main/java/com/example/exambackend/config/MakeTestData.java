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
        Team team4 = new Team("TotalEnergies");
        Team team5 = new Team("Lotto Soudal");

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
        Rider rider11 = new Rider("Sepp Kuss",27,135,225,1200, "Denmark");
        Rider rider12 = new Rider("Dylan Teuns", 29,145,335,930, "Netherlands");
        Rider rider13 = new Rider("Simon Yates", 25,95,295,1045, "France");
        Rider rider14 = new Rider("Magnus Cort", 22,75,245,1350,"USA");
        Rider rider15 = new Rider("Caleb Ewan", 21,130,175,1245 , "Spain");
        Rider rider16 = new Rider("Sam Bennett", 27,55,95,1810,"UK");
        Rider rider17 = new Rider("Kung Stefan", 26,175,160,1130,"Germany");
        Rider rider18 = new Rider("Mark Padun", 24,125,180,1520,"Denmark");
        Rider rider19 = new Rider("Bob Jungles", 23,85,175,1420,"France");
        Rider rider20 = new Rider("Gianni Moscon", 29,140,130,1240, "Italy");

        team1.addRiders(Set.of(rider1,rider8,rider7,rider10));
        team2.addRiders(Set.of(rider2,rider3,rider9,rider14));
        team3.addRiders(Set.of(rider4,rider5,rider6,rider20));
        team4.addRiders(Set.of(rider12,rider16,rider17,rider14));
        team5.addRiders(Set.of(rider11,rider13,rider15,rider18,rider19));

        teamRepository.saveAll(List.of(team1,team2,team3,team4,team5));

    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        riderRepository.deleteAll();
        teamRepository.deleteAll();
        makeTeamsWithRiders();
    }

}
