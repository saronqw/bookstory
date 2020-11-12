package com.example.library.model.api.response;

import com.example.library.entity.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OneCartResponse {
    Long book;
    String username;
    Long count;
    public OneCartResponse(CartEntity cartEntity) {
        this.book = cartEntity.getId().getBook().getId();
        this.username = cartEntity.getId().getUser().getUsername();
        this.count = cartEntity.getCount();
    }
}
