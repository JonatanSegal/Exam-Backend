package com.example.exambackend.repositories;

import com.example.exambackend.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiderRepository extends JpaRepository<Rider,Integer> {
    List<Rider> findRiderByTeamName(String teamName);
}
