package com.example.library.controller;

import com.example.library.exceptions.OrderNotFoundException;
import com.example.library.model.api.response.CartPurchaseWithBooksResponse;
import com.example.library.repository.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    CartRepository cartRepository;
    UserRepository userRepository;
    BookRepository bookRepository;
    OrderRepository orderRepository;
    PurchaseRepository purchaseRepository;

    PurchaseController(
            CartRepository cartRepository,
            UserRepository userRepository,
            BookRepository bookRepository,
            OrderRepository orderRepository,
            PurchaseRepository purchaseRepository
    ) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping(path = "/{orderId}")
    public CollectionModel<CartPurchaseWithBooksResponse> getPurchasesByUsername(@PathVariable Long orderId) {
        List<CartPurchaseWithBooksResponse> list = purchaseRepository.getAllById_Order_Id(orderId);
        if(list.isEmpty()) throw new OrderNotFoundException(orderId);
        return CollectionModel.of(list, linkTo(methodOn(PurchaseController.class).getPurchasesByUsername(orderId)).withSelfRel());
    }

}
