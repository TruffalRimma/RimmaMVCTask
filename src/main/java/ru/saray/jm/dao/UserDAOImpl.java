package ru.saray.jm.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.saray.jm.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Transactional
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Transactional
    public User getUserByID(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void delete(User user) {
        entityManager.remove(entityManager.find(User.class, user.getId()));
    }
}
