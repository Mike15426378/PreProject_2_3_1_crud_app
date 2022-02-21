package testgroup.SERVICE;

import testgroup.Model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User readById(int id);
}
