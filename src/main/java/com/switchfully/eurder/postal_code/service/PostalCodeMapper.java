package com.switchfully.eurder.postal_code.service;

import com.switchfully.eurder.postal_code.api.dto.CreatePostalCodeDto;
import com.switchfully.eurder.postal_code.api.dto.PostalCodeDto;
import com.switchfully.eurder.postal_code.domain.PostalCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PostalCodeMapper {

    private final Logger postalCodeMapperLogger = LoggerFactory.getLogger(PostalCodeMapper.class);


    public PostalCode toPostalCode(CreatePostalCodeDto postalCodeDto) {
        postalCodeMapperLogger.info("CreatePostalCodeDto conversion to PostalCode");
        return new PostalCode(postalCodeDto.getPostalCodeNumber(), postalCodeDto.getCity());
    }

    public PostalCodeDto toDto(PostalCode postalCode) {
        postalCodeMapperLogger.info("PostalCode conversion to PostalCodeDto");
        return new PostalCodeDto(postalCode.getPostalCodeNumber(), postalCode.getCity());
    }
}
