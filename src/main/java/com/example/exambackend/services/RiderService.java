package com.example.exambackend.services;

import com.example.exambackend.DTO.RiderResponse;
import com.example.exambackend.entities.Rider;
import com.example.exambackend.repositories.RiderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RiderService {
    RiderRepository riderRepository;

    public RiderService(RiderRepository riderRepository){
        this.riderRepository = riderRepository;
    }

    public List<RiderResponse> getAllRiders(){
        List<Rider> riderEntities = riderRepository.findAll();
        return  RiderResponse.RiderFromEntityUsingList(riderEntities);
    }

}
