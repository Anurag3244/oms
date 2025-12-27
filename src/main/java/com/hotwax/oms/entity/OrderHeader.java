package com.hotwax.oms.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "order_header")
public class OrderHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private LocalDate orderDate;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "shipping_contact_mech_id")
    private Integer shippingContactMechId;

    @Column(name = "billing_contact_mech_id")
    private Integer billingContactMechId;

    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}