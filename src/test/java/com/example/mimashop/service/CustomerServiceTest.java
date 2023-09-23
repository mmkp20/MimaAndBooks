package com.example.mimashop.service;

import com.example.mimashop.domain.Customer;
import com.example.mimashop.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerServiceTest {
    @Autowired
    CustomerService service;

    @Test
    public void join() throws Exception{
        //given
        Customer customer = new Customer();
        customer.setName("Mike");

        //when
        Long id = service.save(customer);
        //then
        assertEquals("Mike", service.findById(id).get().getName());
    }

    @Test
    public void validate_duplicated_customer() throws Exception{
        //given
        Customer customer1 = new Customer();
        customer1.setName("Nick");

        Customer customer2 = new Customer();
        customer2.setName("Nick");
        //when
        service.save(customer1);
        try {
            service.save(customer2);
        }catch (IllegalStateException e){
            return;
        }
        //then
        fail("IllegalStateException");
    }

}