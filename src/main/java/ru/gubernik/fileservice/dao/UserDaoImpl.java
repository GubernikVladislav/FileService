package ru.gubernik.fileservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.fileservice.model.User;

import javax.persistence.EntityManager;

/**
 * {@inheritDoc}
 */
@Service
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
}
