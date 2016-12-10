package ru.university.examsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task firstTask;

    @ManyToOne
    private Task secondTask;

    @ManyToOne
    private Student student;

    @Column(name = "answer")
    private String answer;

    @Column(name = "mark")
    private Integer mark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getFirstTask() {
        return firstTask;
    }

    public void setFirstTask(Task firstTask) {
        this.firstTask = firstTask;
    }

    public Task getSecondTask() {
        return secondTask;
    }

    public void setSecondTask(Task secondTask) {
        this.secondTask = secondTask;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
