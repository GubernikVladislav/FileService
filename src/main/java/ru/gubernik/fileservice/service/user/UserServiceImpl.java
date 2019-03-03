package ru.gubernik.fileservice.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.fileservice.dao.role.RoleDao;
import ru.gubernik.fileservice.dao.user.UserDao;
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

        if(user == null){
            throw new RuntimeException("USer service error: user cannot be null");
        }

        if(userRole == null) {
            saveRoleUser();
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

    private void saveRoleUser() {
        userRole = roleDao.getRole("USER");
    }
}
