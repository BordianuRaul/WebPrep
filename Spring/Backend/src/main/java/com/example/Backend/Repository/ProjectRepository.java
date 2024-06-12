package com.example.Backend.Repository;

import com.example.Backend.Model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> findAll(){

        String sql = "SELECT * FROM projects";

        return jdbcTemplate.query(sql, projectRowMapper());

    }

    private RowMapper<Project> projectRowMapper(){
        return (ResultSet resultSet, int rowNumber) -> {
            int id = resultSet.getInt("id");
            int managerId = resultSet.getInt("ProjectManagerId");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String members = resultSet.getString("members");

            return new Project(id, managerId, name, description, members);
        };
    }

    public List<Project> findAllProjectsAUserIsPartOf(int userId) {
        String sql = "SELECT * FROM projects WHERE FIND_IN_SET(?, members)";
        return jdbcTemplate.query(sql, new Object[]{String.valueOf(userId)}, projectRowMapper());
    }

    public Integer getProjectIdByName(String projectName) {
        String sql = "SELECT id FROM projects WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{projectName}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            // Handle case where no result was found, e.g., logging or returning a default value
            return 0; // Or throw an exception or return a default value as per your application's logic
        }
    }

    public void addEmptyProject(String projectName) {
        int managerId = 0;
        String sql = "INSERT INTO projects (projectManagerId, name, description, members) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,managerId, projectName, "", "");
    }

    public boolean isDeveloperInProject(int developerId, int projectId) {
        String sql = "SELECT COUNT(*) FROM projects WHERE id = ? AND FIND_IN_SET(?, members) > 0";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{projectId, String.valueOf(developerId)}, Integer.class);
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public void addDeveloperToProject(int developerId, int projectId) {
        String sql = "UPDATE projects SET members = CONCAT(IFNULL(CONCAT(members, ','), ''), ?) WHERE id = ?";
        jdbcTemplate.update(sql, String.valueOf(developerId), projectId);
    }


}
