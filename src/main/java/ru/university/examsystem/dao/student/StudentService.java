package ru.university.examsystem.dao.student;

import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

public interface StudentService {

    Student findStudentByUser(User user);

    Student save(Student student);

}
