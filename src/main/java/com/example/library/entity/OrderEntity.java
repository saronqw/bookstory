package com.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToMany(
            mappedBy = "id.order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PurchaseEntity> purchases = new ArrayList<>();

    @ManyToOne
    private User user;
}
