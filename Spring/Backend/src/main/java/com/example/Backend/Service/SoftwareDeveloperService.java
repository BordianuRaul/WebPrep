package com.example.Backend.Service;

import com.example.Backend.Model.SoftwareDeveloper;
import com.example.Backend.Repository.SoftwareDeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SoftwareDeveloperService {

    private final SoftwareDeveloperRepository softwareDeveloperRepository;

    @Autowired
    public SoftwareDeveloperService(SoftwareDeveloperRepository softwareDeveloperRepository) {
        this.softwareDeveloperRepository = softwareDeveloperRepository;
    }
    public List<SoftwareDeveloper> findAll() {
        return softwareDeveloperRepository.findAll();
    }

    public int getSoftwareDeveloperId(String name) {
        return softwareDeveloperRepository.getSoftwareDeveloperId(name);
    }
}
