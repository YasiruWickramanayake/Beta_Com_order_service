package com.betacom.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;
    @Column(name = "order_gross_amount")
    private BigDecimal orderGrossAmount;
    @Column(name = "order_gross_amount_currency")
    private String orderGrsAmntCur;
    @Column(name = "order_net_amount")
    private BigDecimal orderNetAmount;
    @Column(name = "order_net_amount_currency")
    private String orderNetAmntCur;
    @Column(name = "order_discount_amount")
    private BigDecimal orderDiscntAmount;
    @Column(name = "order_discount_amount_currency")
    private String orderDiscntAmntCur;
    @OneToMany(mappedBy = "orderEntity")
    private List<OrderItemEntity> orderItemEntities;
    @OneToOne(mappedBy = "orderEntity")
    private DeliveryAddressEntity deliveryAddressEntity;
}
