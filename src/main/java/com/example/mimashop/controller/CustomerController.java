package com.example.mimashop.controller;

import com.example.mimashop.domain.Address;
import com.example.mimashop.domain.Customer;
import com.example.mimashop.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/customers/new")
    public String createForm(Model model){
        model.addAttribute("customerForm", new CustomerForm());
        return "customers/createCustomerForm";
    }

    @PostMapping("/customers/new")
    public String create(@Valid CustomerForm form, BindingResult result){
        if(result.hasErrors()){
            return "customers/createCustomerForm";
        }
        Address address = new Address(form.getStreet(),form.getCity(),form.getState(),form.getZipcode());
        Customer customer = new Customer();
        customer.setName(form.getName());
        customer.setAddress(address);

        service.save(customer);
        return "redirect:/";
    }

    @GetMapping("/customers")
    public String list(Model model){
        List<Customer> customerList = service.findAll();
        model.addAttribute("customers",customerList);
        return "customers/customerList";
    }
}
