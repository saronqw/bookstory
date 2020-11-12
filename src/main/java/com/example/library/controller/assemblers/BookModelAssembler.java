package com.example.library.controller.assemblers;

import com.example.library.controller.BookController;
import com.example.library.model.api.response.BookInfoResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<BookInfoResponse, EntityModel<BookInfoResponse>> {
    @Override
    public EntityModel<BookInfoResponse> toModel(BookInfoResponse book) {
        return EntityModel.of(book, //
                linkTo(methodOn(BookController.class).one(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).all()).withRel("books"));
    }
}
