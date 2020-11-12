package com.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "books")
public class BookEntity implements Serializable {

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull(message = "The value must be not null")
    @Column()
    private String author;

    @OneToMany(
            mappedBy = "id.book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartEntity> carts = new ArrayList<>();

    @NotBlank
    @NotNull(message = "The value must be not null")
    @Column()
    private String cover;

    @NotNull(message = "The value must be not null")
    @Column()
    private Date date;

    @Column()
    private String editor;

    @Column()
    private String genre;

    @Min(value = 1, message = "The value must be positive")
    @Column()
    private Long height;

    @NotNull(message = "The value must be not null")
    @NotBlank
    @Size(min = 13, max = 13)
    @Column()
    private String isbn;

    @NotEmpty
    @NotNull(message = "The value must be not null")
    @Column()
    private String language;

    @Min(value = 1, message = "The value must be positive")
    @Column()
    private Long length;

    @NotNull(message = "The value must be not null")
    @Min(value = 1, message = "The value must be positive")
    @Column(name = "page_num")
    private Long pageNumber;

    @NotNull(message = "The value must be not null")
    @Column()
    private Long price;

    @NotNull(message = "The value must be not null")
    @NotEmpty
    @Column()
    private String publisher;

    @OneToMany(
            mappedBy = "id.book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PurchaseEntity> purchases = new ArrayList<>();

    @Column()
    private Double rating;

    @OneToMany(
            mappedBy = "id.book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RatingEntity> ratings = new ArrayList<>();

    @Column()
    private String series;

    @NotNull(message = "The value must be not null")
    @NotBlank
    @Column()
    private String title;

    @Column()
    private String translator;

    @NotNull(message = "The value must be not null")
    @NotEmpty
    @Column()
    private String url;

    @Min(value = 1, message = "The value must be positive")
    @Column()
    private Long weight;

    @Min(value = 1, message = "The value must be positive")
    @Column()
    private Long width;

    @PrePersist
    void preInsert() {
        if (rating == null) {
            rating = 0.0;
        }
    }
}
