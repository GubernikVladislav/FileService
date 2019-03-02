package ru.gubernik.fileservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.fileservice.dao.RoleDao;
import ru.gubernik.fileservice.dao.UserDao;
import ru.gubernik.fileservice.model.Role;
import ru.gubernik.fileservice.model.User;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private Role userRole;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
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

        userDao.addUser(user);
    }

    /*
     * Сохранение объекта Role с именем USER
     */
    private void getRole() {
        userRole = roleDao.getRole("USER");
    }
}
