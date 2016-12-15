package ru.university.examsystem.dao.task;

import ru.university.examsystem.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();
}
