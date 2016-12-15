package ru.university.examsystem.dao.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

import javax.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findStudentByUser(User user) {
        return studentRepository.findStudentByUser(user);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
