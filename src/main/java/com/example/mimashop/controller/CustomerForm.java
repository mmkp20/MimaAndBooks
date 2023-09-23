package com.example.mimashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class CustomerForm {
    @NotEmpty(message="Name may not be empty.")
    private String name;

    private String street;
    private String city;
    private String state;
    private String zipcode;
}
