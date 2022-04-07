package com.switchfully.eurder.customer.api.dto;

import com.switchfully.eurder.customer.infrastructure.Email;

public interface CustomerDtoInterface {

    public String getFirstName();

    public String getLastName();

    public String getEmailAddress();

    public String getAddress();

    public String getPhoneNumber();
}
