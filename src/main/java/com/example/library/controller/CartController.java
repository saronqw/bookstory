package com.example.library.controller;

import com.example.library.entity.*;
import com.example.library.exceptions.UsernameNotFoundException;
import com.example.library.model.api.request.AddBookToCartRequest;
import com.example.library.model.api.request.ChangeCountForCartRequest;
import com.example.library.model.api.request.DeleteBookFromCartRequest;
import com.example.library.model.api.response.CartPurchaseWithBooksResponse;
import com.example.library.model.api.response.OneCartResponse;
import com.example.library.repository.*;
import lombok.extern.java.Log;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log
@RestController
@RequestMapping(path = "/carts")
public class CartController {

    CartRepository cartRepository;
    UserRepository userRepository;
    BookRepository bookRepository;
    OrderRepository orderRepository;
    PurchaseRepository purchaseRepository;

    CartController(
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

    @PostMapping()
    ResponseEntity<?> addBookToUser(@RequestBody AddBookToCartRequest request) {

        CartPK id = new CartPK(
                bookRepository.getById(request.getBookId()),
                userRepository.getByUsername(request.getUsername())
        );

        CartEntity cartEntity = new CartEntity(
                id,
                request.getCount()
        );

        cartRepository.save(cartEntity);

        return ResponseEntity.created(URI.create(
                ("Book with id equal " + cartEntity.getId().getBook().getId()
                        + " for " + cartEntity.getId().getUser().getUsername()).replaceAll(" ", "%20")
                )
        ).body(new OneCartResponse(cartEntity));
    }

    @PutMapping("/{username}")
    ResponseEntity<?> buyBooks(@PathVariable String username) {

        OrderEntity order = new OrderEntity().setDate(LocalDateTime.now())
                .setUser(userRepository.getByUsername(username));

        orderRepository.save(order);

        List<CartEntity> carts = cartRepository.getAllById_User_Username(username);

        for (CartEntity cart : carts) {
            purchaseRepository.save(new PurchaseEntity()
                .setId(
                    new PurchasePK(
                            cart.getId().getBook(),
                            order
                    )
                )
                .setCount(cart.getCount())
            );
        }

        cartRepository.deleteAll(carts);
        return null;
    }

    @PutMapping()
    ResponseEntity<?> changeCount(@RequestBody ChangeCountForCartRequest request) throws Exception {

        CartEntity updatedCart = Optional.of(cartRepository.getById_Book_IdAndAndId_User_Username(
                    request.getBook(), request.getUsername()
                ))
                .map(cart -> {
                    cart.setCount(request.getCount());
                    return cartRepository.save(cart);
                })
                .orElseThrow(Exception::new);

        return ResponseEntity.ok(new OneCartResponse(updatedCart));
    }

    @DeleteMapping()
    ResponseEntity<?> deleteBookFromCart(@RequestBody DeleteBookFromCartRequest request) {
        cartRepository.deleteById_Book_IdAndAndId_User_Username(request.getBook(), request.getUsername());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{username}")
    public CollectionModel<CartPurchaseWithBooksResponse> getCartsByUsername(@PathVariable String username) {
        List<CartPurchaseWithBooksResponse> list = cartRepository.findAllById_User_Username(username);
        if(list.isEmpty()) throw new UsernameNotFoundException(username);
        return CollectionModel.of(list, linkTo(methodOn(CartController.class).getCartsByUsername(username)).withSelfRel());
    }
}
