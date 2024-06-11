package com.example.Backend.Controller;

import com.example.Backend.Model.SoftwareDeveloper;
import com.example.Backend.Service.SoftwareDeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/ITProjects")
public class Controller {

    private final SoftwareDeveloperService softwareDeveloperService;

    @Autowired
    public Controller(SoftwareDeveloperService softwareDeveloperService) {
        this.softwareDeveloperService = softwareDeveloperService;
    }

    @GetMapping
    public List<SoftwareDeveloper> getSoftwareDevelopers() {
        return softwareDeveloperService.findAll();
    }
}
