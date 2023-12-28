package com.vevohub.integrator.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseInit {

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

    public void insertNewCard(String cardName) {
        String sql = "INSERT INTO vevohub.professional_contacts (name) VALUES (?)";
        jdbcTemplate.update(sql, cardName);
        System.out.println("Inserted new card into database: " + cardName);
    }
}
