package com.example.library.controller;

import com.example.library.controller.assemblers.OrderModelAssembler;
import com.example.library.entity.OrderEntity;
import com.example.library.exceptions.OrderNotFoundException;
import com.example.library.model.api.response.OrderInfoResponse;
import com.example.library.repository.OrderRepository;
import com.example.library.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderModelAssembler assembler;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository, OrderModelAssembler assembler) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.assembler = assembler;
    }

    @GetMapping()
    public CollectionModel<EntityModel<OrderInfoResponse>> all() {

        List<EntityModel<OrderInfoResponse>> orders = orderRepository.findAll().stream()
                .map(order -> assembler.toModel(new OrderInfoResponse(order)))
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<OrderInfoResponse>> getOrdersByUsername(@PathVariable String username) {
        List<OrderInfoResponse> orders = orderRepository.getAllByUser_Username(username);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public EntityModel<OrderInfoResponse> one(@PathVariable Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return EntityModel.of(new OrderInfoResponse(order),
                linkTo(methodOn(OrderController.class).one(id)).withSelfRel()
        );
    }
}
