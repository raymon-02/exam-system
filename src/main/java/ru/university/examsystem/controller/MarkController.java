package ru.university.examsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.university.examsystem.dao.exam.ExamService;
import ru.university.examsystem.dao.student.StudentService;
import ru.university.examsystem.entity.Exam;
import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

import java.util.List;

@Controller
public class MarkController {

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/prepod/exams/{id}", method = RequestMethod.GET)
    public String studentMarks(@PathVariable("id") int id, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findById(id);

        model.addAttribute("student", student);
        model.addAttribute("name", user.getName());

        return "prepod/exams";
    }

    @RequestMapping(value = "/prepod/marks/{id}/{exam_id}", method = RequestMethod.GET)
    public String processStudentExams(@PathVariable("id") long id,
                                      @PathVariable("exam_id") long examId,
                                      Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findById(id);
        Exam exam = student.getExams().stream().filter(e -> e.getId() == examId).findFirst().get();

        model.addAttribute("student", student);
        model.addAttribute("exam", exam);
        model.addAttribute("name", user.getName());

        return "prepod/marks";
    }


    @RequestMapping(value = "/prepod/marks-process/{id}/{exam_id}", method = RequestMethod.POST)
    public String processStudentMarks(@PathVariable("id") long id,
                                      @PathVariable("exam_id") long examId,
                                      @RequestParam("mark") Integer mark) {

        Student student = studentService.findById(id);
        List<Exam> exams = student.getExams();
        Exam exam = student.getExams().stream().filter(e -> e.getId() == examId).findFirst().get();

        exam.setMark(mark);
        examService.save(exam);

        return "redirect:/prepod/marks/" + id + "/" + examId + "?saved";
    }


}
