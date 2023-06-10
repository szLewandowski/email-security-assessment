package com.example.emailsecurityassessment.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    boolean existsByEmail(String address);

    Email findFirstByEmail(String email);
}
