package pl.coderslab.ezhelpdesk.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
    private final pl.coderslab.ezhelpdesk.entity.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.ezhelpdesk.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.coderslab.ezhelpdesk.entity.User getUser() {
        return user;
    }
}