package com.betacom.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private Integer itemId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
    @Column(name = "item_gross_amount")
    private BigDecimal itemGrossAmount;
    @Column(name = "item_gross_amount_currency")
    private String itemGrsAmntCur;
    @Column(name = "item_net_amount")
    private BigDecimal itemNetAmount;
    @Column(name = "item_net_amount_currency")
    private String itemNetAmntCur;
    @Column(name = "item_discount_amount")
    private BigDecimal itemDiscntAmount;
    @Column(name = "item_discount_currency")
    private String itemDiscntAmntCur;
    @Column(name = "item_amnt_per_quantity")
    private BigDecimal amountPerQuantity;
    @Column(name = "item_amnt_cur_per_quantity")
    private String amntCurPerQuantity;
    @Column(name = "quantity")
    private Integer quantity;
}
