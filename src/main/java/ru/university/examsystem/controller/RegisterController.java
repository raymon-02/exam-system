package ru.university.examsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import ru.university.examsystem.dao.student.StudentService;
import ru.university.examsystem.dao.user.UserService;
import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class RegisterController {

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {

        SecurityContextHolder.getContext().setAuthentication(null);

        User user = new User();
        model.addAttribute("user", user);

        return "student/register";
    }

    @RequestMapping(value = "/register-process", method = RequestMethod.POST)
    public String registerProcess(@Valid @ModelAttribute("user") User user, BindingResult result, SessionStatus status,
                                  HttpServletRequest request, HttpServletResponse response) {

        if (userService.findByUsername(user.getUsername()) != null) {
            result.rejectValue("username", "existinguser", "User with that username already exists");
            return "student/register";
        }

        user.setRole("STUDENT");

        Student student = new Student();
        student.setUser(user);
        student.setExams(new ArrayList<>());

        studentService.save(student);
        status.setComplete();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticate = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticate);


        return "redirect:/student/profile";
    }


}
