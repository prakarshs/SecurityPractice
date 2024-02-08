package com.Security.User.Service;

import com.Security.User.Constants.UserConstants;
import com.Security.User.Entity.User;
import com.Security.User.Entity.UserVerification;
import com.Security.User.Events.UserActivationPublisher;
import com.Security.User.Misc.Calculations;
import com.Security.User.Model.UserRequest;
import com.Security.User.Repository.UserRepository;
import com.Security.User.Repository.VerificationRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;


@Service
@Log4j2
public class UserServiceIMPL implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private VerificationRepository verificationRepository;

    @Override
    public User create(UserRequest userRequest, HttpServletRequest request) {
        log.info("CREATING USER...");
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .state(UserConstants.INACTIVE)
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
        log.info("SAVING USER...");
        userRepository.save(user);

        publisher.publishEvent(new UserActivationPublisher(user, templateUrl(request)));

        return user;
    }

    private String templateUrl(HttpServletRequest request) {

        log.info(request.getContextPath());
        return "http://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath();


    }

    @Override
    public void addUserVerification(User user, String verificationToken) {
        log.info("CREATING VRFCATION ENTRY...");
        UserVerification userVerification = UserVerification.builder()
                .user(user)
                .verificationToken(verificationToken)
                .expirationTime(Calculations.calculateExpiryTime(Instant.now()))
                .build();

        log.info("SAVING USER VRFCATION...");
        verificationRepository.save(userVerification);

    }

    @Override
    public boolean activateUser(String token) {
        log.info("CHECKING FOR TOKEN IN DB...");
        UserVerification userVerification = verificationRepository.findByVerificationToken(token);

        if (userVerification == null) return false;
        log.info("GETTING TOKEN USER");
        User user = userVerification.getUser();
        if((userVerification.getExpirationTime().getTime() - Date.from(Instant.now()).getTime()) <= 0){
            verificationRepository.delete(userVerification);
            log.info("TOKEN EXPIRED");
            return false;
        }
        user.setState(UserConstants.ACTIVE);
        log.info("CHANGING STATE TO ACTIVE");
        userRepository.save(user);
        return true;
    }
}