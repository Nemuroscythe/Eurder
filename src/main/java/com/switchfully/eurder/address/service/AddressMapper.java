package com.switchfully.eurder.address.service;

import com.switchfully.eurder.address.api.dto.AddressDto;
import com.switchfully.eurder.address.api.dto.CreateAddressDto;
import com.switchfully.eurder.address.domain.Address;
import com.switchfully.eurder.postal_code.service.PostalCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private final Logger addressMapperLogger = LoggerFactory.getLogger(AddressMapper.class);

    private final PostalCodeMapper postalCodeMapper;

    public AddressMapper(PostalCodeMapper postalCodeMapper) {
        this.postalCodeMapper = postalCodeMapper;
    }


    public Address toAddress(CreateAddressDto createAddressDto) {
        addressMapperLogger.info("CreateAddressDto conversion to Address");
        return new Address(
                createAddressDto.getStreetName(),
                createAddressDto.getStreetNumber(),
                postalCodeMapper.toPostalCode(createAddressDto.getPostalCode())
        );
    }

    public AddressDto toDto(Address address) {
        addressMapperLogger.info("Address conversion to AddressDto");
        return new AddressDto(
                address.getStreetName(),
                address.getStreetNumber(),
                postalCodeMapper.toDto(address.getPostalCode())
        );
    }
}
