package com.garima.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String showRegistration() {
        return "registration";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, 
                           @RequestParam String email, 
                           @RequestParam String password) {

        String sql = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, name, email, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
public String login(@RequestParam String email, @RequestParam String password, Model model) {
    String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
    
    try {
        Student student = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, (rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            return s;
        });

        if (student != null) {
            model.addAttribute("student", student);
            return "display";  
        } else {
            return "login";
        }
    } catch (Exception e) {
        return "login";
    }
}
}
