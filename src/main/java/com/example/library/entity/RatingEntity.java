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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "ratings")
public class RatingEntity implements Serializable {

    @EmbeddedId
    private RatingPK id;

    @Column()
    @Min(value = 1, message = "The value must be range of 1 to 10")
    @Max(value = 10, message = "The value must be range of 1 to 10")
    private Integer rating;

    @NotEmpty
    @Size(min = 32, max = 512)
    @Column()
    private String review;
}
