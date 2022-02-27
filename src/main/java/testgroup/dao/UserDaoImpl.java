package testgroup.dao;

import testgroup.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    public void delete(int id) {
        em.remove(em.find(User.class, id));
        em.flush();
    }

    @Override
    public void edit(int id, User updatedUser) {
        em.merge(updatedUser);
        em.flush();
    }

    @Override
    public User readById(int id) {
        return em.find(User.class, id);
    }
}
