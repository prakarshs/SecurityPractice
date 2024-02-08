package com.Security.User.Controller;

import com.Security.User.Entity.User;
import com.Security.User.Model.UserRequest;
import com.Security.User.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    private String test(){
        return "IT WORKS.";
    }

    @PostMapping("/create")
    private ResponseEntity<String> create(@RequestBody UserRequest userRequest, final HttpServletRequest request){
        User user = userService.create(userRequest, request);
        return new ResponseEntity<>("USER CREATED:" +user, HttpStatus.OK);
    }

    @GetMapping("/activate")
    private ResponseEntity<String> activate(@RequestParam(name = "token") String token){
        boolean activationState = userService.activateUser(token);
        if(activationState)
            return new ResponseEntity<>("USER IS ACTIVATED.",HttpStatus.OK);
        else return new ResponseEntity<>("USER IS NOT ACTIVATED.",HttpStatus.OK);
    }
}
