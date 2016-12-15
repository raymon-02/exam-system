package ru.university.examsystem.dao.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.university.examsystem.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
