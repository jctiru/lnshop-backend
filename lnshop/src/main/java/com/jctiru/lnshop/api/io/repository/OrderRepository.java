package com.jctiru.lnshop.api.io.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jctiru.lnshop.api.io.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	OrderEntity findByOrderId(String orderId);

	Page<OrderEntity> findAllByUser_Email(String email, Pageable page);

}
