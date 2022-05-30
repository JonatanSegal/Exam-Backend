package com.example.exambackend.repositories;

import com.example.exambackend.entities.Rider;
import com.example.exambackend.entities.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    RiderRepository riderRepository;

    @Autowired
    TeamRepository teamRepository;

    @BeforeEach
    void setUp(){
        riderRepository.deleteAll();
        teamRepository.deleteAll();
        Team team1 = new Team("Alpha");
        Team team2 = new Team("Beta");
        Rider rider1 = new Rider("Tim",22,"Germany");
        Rider rider2 = new Rider("Finn", 25, "Sweden");
        team1.addRider(rider1);
        team2.addRider(rider2);

        teamRepository.saveAll(List.of(team1,team2));
    }

    @Test
    public void getRiders(){
        List<Rider> riders = riderRepository.findAll();
        assertEquals(2,riders.size());
    }

    @Test
    public void getRiderFromTeam(){
        List<Rider> rider= riderRepository.findRiderByTeamName("Alpha");
        assertEquals(1,rider.size());
    }

    @Test
    public void wrongTeamGetRider(){
        List<Rider> rider= riderRepository.findRiderByTeamName("Charlie");
        assertEquals(0,rider.size());
    }

}