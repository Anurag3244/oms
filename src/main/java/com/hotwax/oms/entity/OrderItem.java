package com.hotwax.oms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemSeqId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderHeader orderHeader;

    private Integer productId;
    private Integer quantity;
    private String status;
}