package ru.kpfu.itis.ovchinnikov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.ovchinnikov.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByLogin(String login);
}
