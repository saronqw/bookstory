package com.example.library.controller;

import com.example.library.controller.assemblers.BookModelAssembler;
import com.example.library.entity.BookEntity;
import com.example.library.exceptions.BookNotFoundException;
import com.example.library.model.api.response.BookInfoResponse;
import com.example.library.repository.BookRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookModelAssembler assembler;
    private final BookRepository repository;

    public BookController(BookRepository bookRepository, BookModelAssembler assembler) {
        this.repository = bookRepository;
        this.assembler = assembler;
    }

    @GetMapping(produces = "application/json")
    public CollectionModel<EntityModel<BookInfoResponse>> all() {

        List<EntityModel<BookInfoResponse>> books = repository.findAll().stream()
                .map(book -> assembler.toModel(new BookInfoResponse(book)))
                .collect(Collectors.toList());

        return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    ResponseEntity<?> newBook(@RequestBody BookEntity newBook) {

        EntityModel<BookInfoResponse> entityModel = assembler.toModel(new BookInfoResponse(repository.save(newBook)));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping(
            path = "/{id}",
            produces = "application/json"
    )
    @ResponseBody
    public EntityModel<BookInfoResponse> one(@PathVariable Long id) {
        BookEntity book = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return EntityModel.of(new BookInfoResponse(book),
                linkTo(methodOn(BookController.class).one(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).all()).withRel("books")
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceBook(@RequestBody BookEntity newBook, @PathVariable Long id) {

        BookEntity updatedBook = repository.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor())
                            .setTitle(newBook.getTitle())
                            .setPrice(newBook.getPrice())
                            .setPageNumber(newBook.getPageNumber())
                            .setEditor(newBook.getEditor())
                            .setCover(newBook.getCover())
                            .setWeight(newBook.getWeight())
                            .setGenre(newBook.getGenre())
                            .setPublisher(newBook.getPublisher())
                            .setSeries(newBook.getSeries())
                            .setTranslator(newBook.getTranslator())
                            .setLanguage(newBook.getLanguage())
                            .setDate(newBook.getDate())
                            .setHeight(newBook.getHeight())
                            .setWidth(newBook.getWidth())
                            .setLength(newBook.getLength())
                            .setRating(newBook.getRating())
                            .setIsbn(newBook.getIsbn())
                            .setUrl(newBook.getUrl());
                    return repository.save(book);
                })
                .orElseGet(() -> repository.save(newBook.setId(id)));

        EntityModel<BookInfoResponse> entityModel = assembler.toModel(new BookInfoResponse(updatedBook));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}

