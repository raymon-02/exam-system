package ru.university.examsystem.dao.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.university.examsystem.entity.Exam;
import ru.university.examsystem.entity.Student;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findAllExamsByStudent(Student student);
}
