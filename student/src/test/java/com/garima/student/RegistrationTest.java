package com.garima.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RegistrationTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testStudentRegistration() {
        String sql = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, "Test User", "testuser@example.com", "password123");
        assertEquals(1, result);
    }
}
