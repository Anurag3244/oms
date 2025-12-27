package com.hotwax.oms.controller;

import com.hotwax.oms.entity.OrderHeader;
import com.hotwax.oms.entity.OrderItem;
import com.hotwax.oms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;


    @PostMapping
    public ResponseEntity<OrderHeader> createOrder(@RequestBody OrderHeader order) {
        OrderHeader createdOrder = service.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderHeader> getOrder(@PathVariable Integer id) {
        OrderHeader order = service.getOrder(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderHeader> updateOrder(@PathVariable Integer id, @RequestBody OrderHeader order) {
        OrderHeader updatedOrder = service.updateOrder(id, order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/items")
    public ResponseEntity<OrderItem> addItem(@PathVariable Integer id, @RequestBody OrderItem item) {
        try {
            OrderItem createdItem = service.addOrderItem(id, item);
            return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{orderId}/items/{seqId}")
    public ResponseEntity<OrderItem> updateItem(@PathVariable Integer seqId, @RequestBody OrderItem item) {
        OrderItem updatedItem = service.updateOrderItem(seqId, item);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{orderId}/items/{seqId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer seqId) {
        service.deleteOrderItem(seqId);
        return ResponseEntity.noContent().build();
    }
}