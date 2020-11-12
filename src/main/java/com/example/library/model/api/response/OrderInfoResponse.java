package com.example.library.model.api.response;

import com.example.library.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderInfoResponse {
    private LocalDateTime date;
    private Long id;

    public OrderInfoResponse(OrderEntity order) {
        this.id = order.getId();
        this.date = order.getDate();
    }
}
