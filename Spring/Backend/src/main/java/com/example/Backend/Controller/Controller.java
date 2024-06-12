package com.example.Backend.Controller;

import com.example.Backend.Model.Project;
import com.example.Backend.Model.SoftwareDeveloper;
import com.example.Backend.Model.User;
import com.example.Backend.Service.ProjectService;
import com.example.Backend.Service.SoftwareDeveloperService;
import com.example.Backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/ITProjects")
public class Controller {

    private final SoftwareDeveloperService softwareDeveloperService;
    private final UserService userService;
    private final ProjectService projectService;

    @Autowired
    public Controller(SoftwareDeveloperService softwareDeveloperService, UserService userService, ProjectService projectService) {
        this.softwareDeveloperService = softwareDeveloperService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping
    public List<SoftwareDeveloper> getSoftwareDevelopers() {
        return softwareDeveloperService.findAll();
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkLogin(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.checkLogin(user.getName(), user.getPassword()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while checking login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/allProjectsAUserIsPartOf")
    public List<Project> getProjectsAUserIsPartOf(@RequestParam int userId) {
        return projectService.findAllProjectsAUserIsPartOf(userId);
    }

    @PostMapping("/addDeveloperToProject")
    public void addDeveloperToProject(@RequestParam String developerName, @RequestParam String projectName) {
        int developerId = softwareDeveloperService.getSoftwareDeveloperId(developerName);
        int projectId = projectService.getProjectIdByName(projectName);
        projectService.addDeveloperToProject(developerId, projectId, projectName);
    }
}
