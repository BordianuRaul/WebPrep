package com.example.Backend.Repository;

import com.example.Backend.Model.SoftwareDeveloper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SoftwareDeveloperRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SoftwareDeveloperRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SoftwareDeveloper> findAll() {
        String sql = "SELECT * FROM softwaredeveloper";
        return jdbcTemplate.query(sql, softwareDeveloperRowMapper());
    }

    private RowMapper<SoftwareDeveloper> softwareDeveloperRowMapper() {
        return (ResultSet resultSet, int rowNumber) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String skills = resultSet.getString("skills");

            return new SoftwareDeveloper(id, name, age, skills);
        };
    }


}
