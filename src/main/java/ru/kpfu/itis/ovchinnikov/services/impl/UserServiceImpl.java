package ru.kpfu.itis.ovchinnikov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.ovchinnikov.model.User;
import ru.kpfu.itis.ovchinnikov.repository.UserRepository;
import ru.kpfu.itis.ovchinnikov.services.UserService;

/**
 * Created by danil on 30/05/17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String login) {
        return userRepository.findByLogin(login);
    }
}
