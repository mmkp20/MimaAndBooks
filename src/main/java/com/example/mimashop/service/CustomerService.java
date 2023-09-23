package com.example.mimashop.service;

import com.example.mimashop.domain.Customer;
import com.example.mimashop.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("CustomerService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @Transactional
    public Long save(Customer customer){
        validateDuplicateCustomer(customer);
        return repository.save(customer).getId();
    }

    private void validateDuplicateCustomer (Customer customer) {
        List<Customer> customerList = repository.findByNameIgnoreCase(customer.getName());
        if(customerList.size()>0){
            throw  new IllegalStateException("duplicated customer");
        }
    }

    public Optional<Customer> findById(Long id){
        return repository.findById(id);
    }

    public List<Customer> findAll(){
        return repository.findAll();
    }

}
