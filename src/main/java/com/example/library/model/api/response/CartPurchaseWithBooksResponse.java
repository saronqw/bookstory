package com.example.library.model.api.response;

import com.example.library.entity.CartEntity;
import com.example.library.entity.PurchaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CartPurchaseWithBooksResponse {

    private BookInfoResponse book;
    private Long count;

    public CartPurchaseWithBooksResponse(CartEntity cartEntity) {
        this.book = new BookInfoResponse(cartEntity.getId().getBook());
        this.count = cartEntity.getCount();
    }

    public CartPurchaseWithBooksResponse(PurchaseEntity purchaseEntity) {
        this.book = new BookInfoResponse(purchaseEntity.getId().getBook());
        this.count = purchaseEntity.getCount();
    }
}
