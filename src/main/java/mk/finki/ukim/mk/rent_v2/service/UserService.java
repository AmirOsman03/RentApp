package mk.finki.ukim.mk.rent_v2.service;


import mk.finki.ukim.mk.rent_v2.model.User;
import mk.finki.ukim.mk.rent_v2.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
