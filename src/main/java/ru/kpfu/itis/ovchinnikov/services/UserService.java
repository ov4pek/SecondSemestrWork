package ru.kpfu.itis.ovchinnikov.services;

import ru.kpfu.itis.ovchinnikov.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
