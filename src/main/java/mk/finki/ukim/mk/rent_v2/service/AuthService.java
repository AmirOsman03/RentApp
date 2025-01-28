package mk.finki.ukim.mk.rent_v2.service;

import mk.finki.ukim.mk.rent_v2.model.User;

public interface AuthService {
    User login(String username, String password);
}
