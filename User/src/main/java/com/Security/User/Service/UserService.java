package com.Security.User.Service;

import com.Security.User.Entity.User;
import com.Security.User.Model.UserRequest;

public interface UserService {
    User create(UserRequest userRequest);
}
