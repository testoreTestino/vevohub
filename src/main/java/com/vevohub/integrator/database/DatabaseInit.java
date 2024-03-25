package com.vevohub.integrator.database;

import com.vevohub.integrator.database.dao.CandidatesRepository;
import com.vevohub.integrator.database.entity.CandidatesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseInit {


    @Autowired
    private CandidatesRepository candidatesRepository;


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        testConnection();
    }

    private void testConnection() {
        try {
            String sql = "SELECT 1";
            Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
            if (result != null) {
                System.out.println("Database connection successful");
            }
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

}
