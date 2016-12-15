package ru.university.examsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.university.examsystem.dao.exam.ExamService;
import ru.university.examsystem.dao.student.StudentService;
import ru.university.examsystem.dao.task.TaskService;
import ru.university.examsystem.entity.Exam;
import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.Task;
import ru.university.examsystem.entity.User;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/student/exam", method = RequestMethod.GET)
    public String exam(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findByUser(user);
        List<Task> tasks = chooseTasks();

        Exam exam = new Exam();
        exam.setStudent(student);
        exam.setTaskFirst(tasks.get(0));
        exam.setTaskSecond(tasks.get(1));

        examService.save(exam);

        model.addAttribute("name", user.getName());
        model.addAttribute("exam", exam);

        return "student/exam";
    }

    @RequestMapping(value = "/student/exam-process", method = RequestMethod.PUT)
    public String examProcess(@ModelAttribute("exam") Exam examPart) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findByUser(user);
        List<Exam> exams = examService.findAllExamsByStudent(student);

        exams.sort((e1, e2) -> (int) (e1.getId() - e2.getId()));
        Exam exam = exams.get(exams.size() - 1);

        exam.setAnswerFirst(examPart.getAnswerFirst());
        exam.setAnswerSecond(examPart.getAnswerSecond());

        examService.save(exam);


        return "redirect:/student/success";
    }

    @RequestMapping(value = "/student/success", method =RequestMethod.GET)
    public String examSuccess(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", user.getName());

        return "student/exam_success";
    }

    private List<Task> chooseTasks() {
        List<Task> tasks = taskService.findAll();
        Collections.shuffle(tasks);

        return tasks.subList(0, 2);
    }

}
