package ru.gubernik.fileservice.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.fileservice.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

        if(user == null){
            return;
        }

        entityManager.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByName(String userName) {

        if(userName == null || userName.isEmpty()){
            throw new RuntimeException("User dao error: user name cannot be null or empty");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root root = criteriaQuery.from(User.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("userName"), userName));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByActivationCode(String code) {

        if(code == null || code.isEmpty()){
            throw new RuntimeException("Activation code cannot be null or empty");
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root root = criteriaQuery.from(User.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("code"), code));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User user) {

        if(user == null){
            return;
        }

        entityManager.merge(user);
    }

}
