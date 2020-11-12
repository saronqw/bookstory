package com.example.library.model.api.response;

import com.example.library.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoResponse implements Serializable {

    private String author;
    private String cover;
    private Date date;
    private String editor;
    private String genre;
    private Long height;
    private Long id;
    private String isbn;
    private String language;
    private Long length;
    private Long pageNumber;
    private Long price;
    private String publisher;
    private Double rating;
    private String series;
    private String title;
    private String translator;
    private String url;
    private Long weight;
    private Long width;
    public BookInfoResponse(BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        this.price = bookEntity.getPrice();
        this.pageNumber = bookEntity.getPageNumber();
        this.editor = bookEntity.getEditor();
        this.cover = bookEntity.getCover();
        this.weight = bookEntity.getWeight();
        this.genre = bookEntity.getGenre();
        this.publisher = bookEntity.getPublisher();
        this.series = bookEntity.getSeries();
        this.translator = bookEntity.getTranslator();
        this.language = bookEntity.getLanguage();
        this.date = bookEntity.getDate();
        this.height = bookEntity.getHeight();
        this.width = bookEntity.getWidth();
        this.length = bookEntity.getLength();
        this.rating = bookEntity.getRating();
        this.isbn = bookEntity.getIsbn();
        this.url = bookEntity.getUrl();
    }
}
