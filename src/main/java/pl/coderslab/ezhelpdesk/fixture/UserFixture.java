package pl.coderslab.ezhelpdesk.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.ezhelpdesk.entity.User;
import pl.coderslab.ezhelpdesk.repository.UserRepository;

@Component
public class UserFixture {
    @Autowired
    UserRepository userRepository;

    public void initData() {


    }
}
