package com.example.Backend.Service;

import com.example.Backend.Model.Project;
import com.example.Backend.Model.User;
import com.example.Backend.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public List<Project> findAllProjectsAUserIsPartOf(int userId) {
        return projectRepository.findAllProjectsAUserIsPartOf(userId);
    }

    public void addDeveloperToProject (int developerId, int projectId, String projectName) {
        if(projectId == 0)
        {
            this.projectRepository.addEmptyProject(projectName);
            projectId = this.projectRepository.getProjectIdByName(projectName);
        }
        if(!this.isDeveloperInProject(developerId, projectId) && developerId != 0)
        {
            this.projectRepository.addDeveloperToProject(developerId, projectId);
        }

    }

    public int getProjectIdByName(String projectName) {
        return projectRepository.getProjectIdByName(projectName);
    }

    public boolean isDeveloperInProject(int developerId, int projectId) {
        return projectRepository.isDeveloperInProject(developerId, projectId);
    }

}
