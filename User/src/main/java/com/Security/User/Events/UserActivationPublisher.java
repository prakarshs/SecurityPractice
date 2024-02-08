package com.Security.User.Events;

import com.Security.User.Entity.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;
@Data
public class UserActivationPublisher extends ApplicationEvent {
    private String verificationUrl;
    private User user;
    public UserActivationPublisher(User user, String verificationUrl) {
        super(user);
        this.user = user;
        this.verificationUrl = verificationUrl;
    }

}
