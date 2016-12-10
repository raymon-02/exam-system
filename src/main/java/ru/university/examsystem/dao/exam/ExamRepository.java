package ru.university.examsystem.dao.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.university.examsystem.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
