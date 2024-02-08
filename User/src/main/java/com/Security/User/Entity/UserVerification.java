package com.Security.User.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "USER_VERIFICATION")
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verificationId;
    private String verificationToken;
    private Date expirationTime;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_VERIFICATION"))
    private User user;
}
