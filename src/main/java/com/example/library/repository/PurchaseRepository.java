package com.example.library.repository;

import com.example.library.entity.PurchaseEntity;
import com.example.library.entity.PurchasePK;
import com.example.library.model.api.response.CartPurchaseWithBooksResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, PurchasePK> {
    List<CartPurchaseWithBooksResponse> getAllById_Order_Id(Long id);
}
