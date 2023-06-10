package com.example.emailsecurityassessment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {

    boolean existsByAddress(String address);

    Domain findFirstByAddress(String link);
}
