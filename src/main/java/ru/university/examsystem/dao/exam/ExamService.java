package ru.university.examsystem.dao.exam;

import ru.university.examsystem.entity.Exam;
import ru.university.examsystem.entity.Student;

import java.util.List;

public interface ExamService {

    List<Exam> findAllExamsByStudent(Student student);

    Exam save(Exam exam);
}
