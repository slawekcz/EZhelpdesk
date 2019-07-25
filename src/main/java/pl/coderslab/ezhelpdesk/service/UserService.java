package pl.coderslab.ezhelpdesk.service;

import pl.coderslab.ezhelpdesk.entity.User;

import java.util.List;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);

    Object findFirstById(Long id);

    List<User> findAll();

    void editUser(User user);
}