package ru.university.examsystem.dao.user;

import ru.university.examsystem.entity.User;

public interface UserService {

    User findByUsername(String username);
}
