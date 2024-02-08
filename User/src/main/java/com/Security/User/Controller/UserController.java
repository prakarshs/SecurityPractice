package com.Security.User.Controller;

import com.Security.User.Entity.User;
import com.Security.User.Model.UserRequest;
import com.Security.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    private String create(@RequestBody UserRequest userRequest){
        User user = userService.create(userRequest);
        return "USER REGISTERED SUCCESSFULLY: "+user;
    }
}
