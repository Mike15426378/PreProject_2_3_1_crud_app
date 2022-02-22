package testgroup.DAO;

import testgroup.Model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();
    void add(User user);
    //
    User delete(int id);
    //
    void edit(User user);
    User readById(int id);
}
