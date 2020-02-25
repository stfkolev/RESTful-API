package com.kolev.demo.controllers;

import com.kolev.demo.exceptions.OrderAlreadyExists;
import com.kolev.demo.exceptions.OrderNotFound;
import com.kolev.demo.models.Order;
import com.kolev.demo.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> list() {
        return this.orderRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var order = this.orderRepository.findById(id).orElse(null);

        if(order == null) {
            throw new OrderNotFound();
        }

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Order order) {
        var iOrderNameExists = this.orderRepository.findByOrderName(order.getOrderName());

        if(iOrderNameExists != null) {
            throw new OrderAlreadyExists();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderRepository.saveAndFlush(order));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.orderRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Order order) {
        /*! Get the order */
        var existingOrder = this.orderRepository.findById(id).orElse(null);

        if(existingOrder == null) {
            throw new OrderNotFound();
        }

        /*! Replace all props and save them to existingOrder, while ignoring order_id */
        BeanUtils.copyProperties(order, existingOrder, "order_id");

        /*! Update the order */
        return ResponseEntity.status(HttpStatus.OK).body(this.orderRepository.saveAndFlush(existingOrder));
    }
}
