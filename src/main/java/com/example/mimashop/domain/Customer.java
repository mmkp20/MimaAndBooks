package com.example.mimashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Customer {

    @Id @GeneratedValue
    @Column(name="customer_id")
    private Long id;

    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

}
