package com.example.library.repository;

import com.example.library.entity.OrderEntity;
import com.example.library.model.api.response.OrderInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderInfoResponse> getAllByUser_Username(String username);
}
