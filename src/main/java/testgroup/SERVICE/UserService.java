package testgroup.SERVICE;

import testgroup.Model.User;
import java.util.List;

public interface UserService {
    List<User> allUsers();
    void add(User user);
    void delete(int id);
    void edit(int id, User user);
    User readById(int id);
}
