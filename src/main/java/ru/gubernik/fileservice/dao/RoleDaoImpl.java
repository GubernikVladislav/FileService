package ru.gubernik.fileservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gubernik.fileservice.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * {@inheritDoc}
 */
@Service
public class RoleDaoImpl implements RoleDao {

    private final EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role getRole(String roleName) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root root = criteriaQuery.from(Role.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("role"), roleName));

        TypedQuery query = entityManager.createQuery(criteriaQuery);

        return (Role) query.getSingleResult();
    }
}
