package com.betacom.repository.dao;

import com.betacom.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCommandJPARepo extends JpaRepository<OrderEntity, Integer> {
 OrderEntity getByOrderId(Integer orderId);
}
