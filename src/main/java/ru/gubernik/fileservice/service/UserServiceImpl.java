package ru.gubernik.fileservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.fileservice.dao.RoleDao;
import ru.gubernik.fileservice.dao.UserDao;
import ru.gubernik.fileservice.model.Role;
import ru.gubernik.fileservice.model.User;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private Role userRole;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addUser(User user) {

        if(userRole == null) {
            getRole();
        }

        user.setRole(userRole);
        user.setIsActive(true);
        user.setPassword(encoder.encode(user.getPassword()));

        userDao.addUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> userList() {
        return userDao.findAll();
    }

    /*
     * Сохранение объекта Role с именем USER
     */
    private void getRole() {
        userRole = roleDao.getRole("USER");
    }
}
