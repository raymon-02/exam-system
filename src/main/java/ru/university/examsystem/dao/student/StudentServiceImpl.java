package ru.university.examsystem.dao.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.examsystem.entity.Student;
import ru.university.examsystem.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student findByUser(User user) {
        return studentRepository.findByUser(user);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
