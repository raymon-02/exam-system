package ru.university.examsystem.dao.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.university.examsystem.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
