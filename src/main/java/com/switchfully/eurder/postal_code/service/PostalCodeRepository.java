package com.switchfully.eurder.postal_code.service;

import com.switchfully.eurder.postal_code.domain.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {

}
