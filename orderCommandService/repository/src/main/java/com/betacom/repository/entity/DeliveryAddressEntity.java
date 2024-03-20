package com.betacom.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_delivery_address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delivery_address_id")
    private Integer deliveryAddressId;
    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
    @Column(name = "building_number")
    private String buildingNumber;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "postal_code")
    private String postalCode;
}
