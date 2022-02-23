package testgroup.DAO;

import testgroup.Model.User;
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

    //добавим пользователя в хранилище
    @Override
    public void add(User user) {
        em.persist(user);
        em.flush();
    }

    //удаляем пользователя по ключю из из бд
    @Override
    public void delete(int id) {
        User user = readById(id);
        em.remove(user);
        em.flush();
    }

    //обновим данные о пользователе, добавив по его старому ключу новую инфу
    @Override
    public void edit(int id, User updatedUser) {
        User userToBeUpdated = readById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setSurname(updatedUser.getSurname());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        em.merge(userToBeUpdated);
        em.flush();
    }

    //достанем пользователя по id из хранилища
    @Override
    public User readById(int id) {
        return em.find(User.class, id);
    }
}
