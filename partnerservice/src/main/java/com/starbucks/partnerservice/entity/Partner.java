package com.starbucks.partnerservice.entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class Partner {

    @NotNull(message = "Id should not be null for Partner")
    Integer id;

    String name;

    @Email(message = "Invalid Email Id")
    String eMail;
}
