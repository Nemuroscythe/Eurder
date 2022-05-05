package com.switchfully.eurder.postal_code.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, UUID> {

}
