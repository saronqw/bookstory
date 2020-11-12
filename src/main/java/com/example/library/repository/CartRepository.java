package com.example.library.repository;

import com.example.library.entity.CartEntity;
import com.example.library.entity.CartPK;
import com.example.library.model.api.response.CartPurchaseWithBooksResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, CartPK> {
    @Transactional
    void deleteById_Book_IdAndAndId_User_Username(Long id, String username);

    List<CartPurchaseWithBooksResponse> findAllById_User_Username(String username);

    List<CartEntity> getAllById_User_Username(String username);

    CartEntity getById_Book_IdAndAndId_User_Username(Long id, String username);
//    void deleteById(CartPK id);
}
