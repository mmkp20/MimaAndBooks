package com.example.mimashop.repository;

import com.example.mimashop.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    List<Customer> findByNameIgnoreCase (String name);

//    public List<Customer> findByName(String name){
//        return em.createQuery("select c from Customer c where c.name= :name", Customer.class)
//                .setParameter("name", name)
//                .getResultList();
//    }
}
