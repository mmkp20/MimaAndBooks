package com.example.mimashop.repository;

import com.example.mimashop.domain.Order;
import com.example.mimashop.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);
    @Query("Select o from Order o join o.customer c where o.status = :status and c.name like :name")
    List<Order> findOrders(@Param("status") OrderStatus status, @Param("name") String name);

}
