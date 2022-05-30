package com.example.exambackend.repositories;

import com.example.exambackend.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider,Integer> {
}
