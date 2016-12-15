package ru.university.examsystem.dao.student;

import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

import java.util.List;

public interface StudentService {

    Student findById(long id);

    Student findByUser(User user);

    List<Student> findAll();

    Student save(Student student);

}
