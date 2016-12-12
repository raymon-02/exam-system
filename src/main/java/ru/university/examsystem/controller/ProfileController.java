package ru.university.examsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

    @RequestMapping(value = "student/profile")
    public String studentProfile() {
        return "student/profile";
    }
}
