package com.Security.User.Events;

import com.Security.User.Entity.User;
import com.Security.User.Service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Log4j2
public class UserActivationListener implements ApplicationListener<UserActivationPublisher> {

    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(UserActivationPublisher event) {
        User user = event.getUser();
        String verificationToken = UUID.randomUUID().toString();

        userService.addUserVerification(user,verificationToken);

        String sendingUrl = event.getVerificationUrl() + "/users/activate?token=" + verificationToken;
        log.info("CLICK HERE TO VERIFY USER : " + sendingUrl);


    }
}
