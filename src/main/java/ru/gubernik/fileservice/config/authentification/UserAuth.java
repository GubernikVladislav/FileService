package ru.gubernik.fileservice.config.authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gubernik.fileservice.dao.user.UserDao;
import ru.gubernik.fileservice.model.User;

@Service
public class UserAuth implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserAuth(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userDao.getUserByName(s);

        return new UserPrincipal(user);
    }
}
