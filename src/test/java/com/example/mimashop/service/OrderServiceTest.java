package com.example.mimashop.service;

import com.example.mimashop.domain.Address;
import com.example.mimashop.domain.Customer;
import com.example.mimashop.domain.Order;
import com.example.mimashop.domain.OrderStatus;
import com.example.mimashop.domain.item.Book;
import com.example.mimashop.domain.item.Item;
import com.example.mimashop.exception.OutOfStockException;
import com.example.mimashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void orderProduct() throws Exception{
        //given
        Customer customer = createCustomer();

        Book book = createBook("Test Book1", 50, 10);

        int orderCount = 3;
        //when
        Long orderId = orderService.order(customer.getId(), book.getId(), orderCount);
        //then
        Order order = orderRepository.findById(orderId).get();

        assertEquals(OrderStatus.ORDER, order.getStatus());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(book.getPrice()*orderCount,order.getTotalPrice());
        assertEquals(7, book.getQuantity());
    }

    @Test
    public void whenOutOfStockExceptionThrown() throws Exception{
        //given
        Customer customer = createCustomer();
        Item item = createBook("Test Book1", 50, 10);

        int orderCount = 11;
        //when
        Exception exception = assertThrows(OutOfStockException.class, () ->{
            orderService.order(customer.getId(), item.getId(), orderCount);
        });

        //then
        assertEquals("need more stock", exception.getMessage());
    }

    @Test
    public void cancelOrder() throws Exception{
        //given
        Customer customer = createCustomer();
        Book item = createBook("Test2", 20, 10);

        int orderCount = 2;

        Long id = orderService.order(customer.getId(),item.getId(), orderCount);

        //when
        orderService.cancelOrder(id);

        //then
        Order order = orderRepository.findById(id).get();

        assertEquals(OrderStatus.CANCEL, order.getStatus());
        assertEquals(10, item.getQuantity());
    }
    private Book createBook (String name, int price, int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setQuantity(quantity);
        itemService.saveItem(book);
        return book;
    }

    private Customer createCustomer () {
        Customer customer = new Customer();
        customer.setName("Mima");
        customer.setAddress(new Address("123 xx street","philly","PA","12345"));
        customerService.save(customer);
        return customer;
    }

}