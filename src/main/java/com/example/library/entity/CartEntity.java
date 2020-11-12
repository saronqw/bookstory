package com.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "carts")
public class CartEntity implements Serializable {

    @EmbeddedId
    private CartPK id;

    @Column()
    @Min(value = 1, message = "The value must be positive")
    @NotNull(message = "The value must be not null")
    private Long count;
}


