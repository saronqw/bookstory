package com.example.library.repository;

import com.example.library.entity.CartEntity;
import com.example.library.entity.RatingPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<CartEntity, RatingPK> {

}
