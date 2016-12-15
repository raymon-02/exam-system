package ru.university.examsystem.dao.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.examsystem.entity.Exam;
import ru.university.examsystem.entity.Student;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> findAllExamsByStudent(Student student) {
        return examRepository.findAllExamsByStudent(student);
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }
}
