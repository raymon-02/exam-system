package ru.university.examsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.university.examsystem.dao.student.StudentService;
import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "student/profile", method = RequestMethod.GET)
    public String studentProfile(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findByUser(user);

        model.addAttribute("exams", student.getExams());
        model.addAttribute("name", user.getName());

        return "student/profile";
    }

    @RequestMapping(value = "prepod/profile", method = RequestMethod.GET)
    public String prepodProfile(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Student> students = studentService.findAll();

        model.addAttribute("students", students);
        model.addAttribute("name", user.getName());

        return "prepod/profile";
    }
}
