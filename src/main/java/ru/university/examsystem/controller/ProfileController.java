package ru.university.examsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.university.examsystem.dao.exam.ExamService;
import ru.university.examsystem.dao.student.StudentService;
import ru.university.examsystem.entity.Exam;
import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "student/profile")
    public String studentProfile(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findStudentByUser(user);

        model.addAttribute("exams", student.getExams());
        model.addAttribute("name", user.getName());

        return "student/profile";
    }

    @RequestMapping(value = "prepod/profile")
    public String prepodProfile(Model model) {
        return "prepod/profile";
    }
}
