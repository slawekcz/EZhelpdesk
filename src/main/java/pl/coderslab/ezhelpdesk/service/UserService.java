package pl.coderslab.ezhelpdesk.service;

import pl.coderslab.ezhelpdesk.entity.User;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}