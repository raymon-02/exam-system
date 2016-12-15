package ru.university.examsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "exam")
public class Exam implements Serializable {

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
}
