package com.example.mimashop.service;

import com.example.mimashop.domain.Customer;
import com.example.mimashop.domain.Delivery;
import com.example.mimashop.domain.Order;
import com.example.mimashop.domain.OrderItem;
import com.example.mimashop.domain.item.Item;
import com.example.mimashop.repository.CustomerRepository;
import com.example.mimashop.repository.ItemRepository;
import com.example.mimashop.repository.OrderRepository;
import com.example.mimashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("OrderService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long customer_id, Long item_id, int count){
        // Search Entity
        Customer customer = customerRepository.findById(customer_id).get();
        Item item = itemRepository.findById(item_id).get();

        // create delivery info
        Delivery delivery = new Delivery();
        delivery.setAddress(customer.getAddress());

        // create OrderItem
        OrderItem orderItem =OrderItem.createOrderItem(item, item.getPrice(), count );

        // create Order
        Order order = Order.createOrder(customer, delivery, orderItem);

        // save the order
        return orderRepository.save(order).getId();
    }

    @Transactional
    public void cancelOrder(Long id){
        // search the entity
        Order order = orderRepository.findById(id).get();
        // cancel the order
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findOrders(orderSearch.getOrderStatus(), orderSearch.getCustomerName());
    }

}
