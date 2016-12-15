package ru.university.examsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task taskFirst;

    @ManyToOne
    private Task taskSecond;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "answer_first")
    private String answerFirst;

    @Column(name = "answer_second")
    private String answerSecond;

    @Column(name = "mark")
    private Integer mark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTaskFirst() {
        return taskFirst;
    }

    public void setTaskFirst(Task taskFirst) {
        this.taskFirst = taskFirst;
    }

    public Task getTaskSecond() {
        return taskSecond;
    }

    public void setTaskSecond(Task taskSecond) {
        this.taskSecond = taskSecond;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAnswerFirst() {
        return answerFirst;
    }

    public void setAnswerFirst(String answerFirst) {
        this.answerFirst = answerFirst;
    }

    public String getAnswerSecond() {
        return answerSecond;
    }

    public void setAnswerSecond(String answerSecond) {
        this.answerSecond = answerSecond;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
