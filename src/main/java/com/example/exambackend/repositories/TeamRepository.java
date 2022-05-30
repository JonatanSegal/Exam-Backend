package com.example.exambackend.repositories;

import com.example.exambackend.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findTeamByName(String name);
}
