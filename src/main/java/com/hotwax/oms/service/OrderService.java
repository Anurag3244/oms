package com.hotwax.oms.service;

import com.hotwax.oms.entity.OrderHeader;
import com.hotwax.oms.entity.OrderItem;
import com.hotwax.oms.repository.OrderHeaderRepository;
import com.hotwax.oms.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderHeaderRepository orderRepository;

    @Autowired
    private OrderItemRepository itemRepository;

    public OrderHeader createOrder(OrderHeader order) {
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrderHeader(order);
            }
        }
        return orderRepository.save(order);
    }

    public OrderHeader getOrder(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderHeader updateOrder(Integer id, OrderHeader newDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setShippingContactMechId(newDetails.getShippingContactMechId());
            order.setBillingContactMechId(newDetails.getBillingContactMechId());
            return orderRepository.save(order);
        }).orElse(null);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public OrderItem addOrderItem(Integer orderId, OrderItem item) {
        OrderHeader order = orderRepository.findById(orderId).orElseThrow();
        item.setOrderHeader(order);
        return itemRepository.save(item);
    }

    public OrderItem updateOrderItem(Integer seqId, OrderItem newItem) {
        return itemRepository.findById(seqId).map(item -> {
            item.setQuantity(newItem.getQuantity());
            item.setStatus(newItem.getStatus());
            return itemRepository.save(item);
        }).orElse(null);
    }

    public void deleteOrderItem(Integer seqId) {
        itemRepository.deleteById(seqId);
    }
}