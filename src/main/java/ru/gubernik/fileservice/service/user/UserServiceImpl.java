package ru.gubernik.fileservice.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gubernik.fileservice.dao.role.RoleDao;
import ru.gubernik.fileservice.dao.user.UserDao;
import ru.gubernik.fileservice.model.Role;
import ru.gubernik.fileservice.model.User;
import ru.gubernik.fileservice.service.email.EmailSender;
import ru.gubernik.fileservice.service.email.SenderThread;

import java.util.List;
import java.util.UUID;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private Role userRole;
    private final PasswordEncoder encoder;
    private final EmailSender emailSender;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder encoder, EmailSender emailSender) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
        this.emailSender = emailSender;
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

        String activationCode = UUID.randomUUID().toString();

        SenderThread senderthread = new SenderThread(emailSender, activationCode, user.getEmail());
        senderthread.start();

        user.setIsActive(false);
        user.setCode(activationCode);
        user.setRole(userRole);
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

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void activateUser(String code) {

        User user = userDao.getUserByActivationCode(code);
        user.setIsActive(true);
        userDao.updateUser(user);
    }

    private void saveRoleUser() {
        userRole = roleDao.getRole("USER");
    }
}
