package vn.jewel.shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.jewel.shop.dto.UserRegistrationDto;
import vn.jewel.shop.model.User;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    User findByUserName(String username);

    User save(UserRegistrationDto registration);
}