package mk.finki.ukim.mk.rent_v2.service.impl;

import mk.finki.ukim.mk.rent_v2.model.User;
import mk.finki.ukim.mk.rent_v2.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.rent_v2.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.rent_v2.repository.UserRepository;
import mk.finki.ukim.mk.rent_v2.service.AuthService;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
