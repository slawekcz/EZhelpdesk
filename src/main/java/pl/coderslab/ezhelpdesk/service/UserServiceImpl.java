package pl.coderslab.ezhelpdesk.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.ezhelpdesk.entity.Role;
import pl.coderslab.ezhelpdesk.entity.User;
import pl.coderslab.ezhelpdesk.repository.RoleRepository;
import pl.coderslab.ezhelpdesk.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User findFirstById(Long id) {
        return userRepository.findFirstById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void editUser(User user) {
        User userInDB = userRepository.findFirstById(user.getId());
        user.setPassword(userInDB.getPassword());
        user.setRoles(userInDB.getRoles());
        user.setEnabled(1);
        userRepository.save(user);
    }
}