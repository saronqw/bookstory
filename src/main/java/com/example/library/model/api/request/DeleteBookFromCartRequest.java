package com.example.library.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBookFromCartRequest {
//    public DeleteBookInCartRequest(CartEntity cartEntity) {
//        this.book = cartEntity.getId().getBook().getId();
//        this.username = cartEntity.getId().getUser().getUsername();
//        this.count = cartEntity.getCount();
//    }

    Long book;
    String username;
}
