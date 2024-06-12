package com.example.Backend.Repository;

import com.example.Backend.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User checkLogin(String username, String password) throws Exception {
        String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, userRowMapper());
        } catch (Exception e) {
            throw new Exception("User not found or invalid credentials");
        }
    }


    private RowMapper<User> userRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            int userId = rs.getInt("id");
            String name = rs.getString("name");
            String password = rs.getString("password");

            return new User(userId, name, password);
        };
    }


}
