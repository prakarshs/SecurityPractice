package com.Security.User.Repository;

import com.Security.User.Entity.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<UserVerification,Long> {
    UserVerification findByVerificationToken(String token);
}
