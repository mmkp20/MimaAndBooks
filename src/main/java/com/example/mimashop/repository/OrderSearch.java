package com.example.mimashop.repository;

import com.example.mimashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String customerName;
    private OrderStatus orderStatus; // [ORDER, CANCEL]
}
