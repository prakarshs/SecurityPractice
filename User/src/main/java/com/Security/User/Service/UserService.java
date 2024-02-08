package com.Security.User.Service;

import com.Security.User.Entity.User;
import com.Security.User.Model.UserRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    User create(UserRequest userRequest, HttpServletRequest request);

    void addUserVerification(User user, String verificationToken);

    boolean activateUser(String token);
}
