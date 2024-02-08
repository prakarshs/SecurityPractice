package com.Security.User.Service;

import com.Security.User.Entity.User;
import com.Security.User.Model.UserRequest;
import com.Security.User.Repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceIMPL implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User create(UserRequest userRequest) {
        log.info("CREATING USER...");
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
        log.info("SAVING USER...");
        userRepository.save(user);
        return user;
    }
}
