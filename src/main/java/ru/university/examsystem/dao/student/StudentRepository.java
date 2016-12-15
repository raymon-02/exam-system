package ru.university.examsystem.dao.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findById(long id);

    Student findByUser(User user);

}
