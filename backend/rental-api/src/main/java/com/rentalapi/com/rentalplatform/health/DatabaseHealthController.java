
package com.rentalapi.com.rentalplatform.health;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DatabaseHealthController {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseHealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/api/health/database")
    public Map<String, Object> checkDatabase() {

        Integer result = jdbcTemplate.queryForObject(
                "SELECT 1",
                Integer.class
        );

        return Map.of(
                "database", "Neon PostgreSQL",
                "status", "UP",
                "connection", result == 1
        );
    }
}
