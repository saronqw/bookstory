package com.example.library.controller;

import com.example.library.controller.assemblers.UserModelAssembler;
import com.example.library.entity.User;
import com.example.library.exceptions.UsernameNotFoundException;
import com.example.library.model.api.response.CartPurchaseWithBooksResponse;
import com.example.library.model.api.response.UserInfoResponse;
import com.example.library.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserModelAssembler assembler;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public UserController(UserRepository repository, UserModelAssembler assembler, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.assembler = assembler;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public CollectionModel<EntityModel<UserInfoResponse>> all() {

        List<EntityModel<UserInfoResponse>> users = repository.findAll().stream()
                .map(user -> assembler.toModel(new UserInfoResponse(user)))
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PutMapping("/{username}")
    ResponseEntity<?> changeUser(@RequestBody User newUser, @PathVariable String username) {


        User updatedUser = repository.findByUsername(username)
                .map(user -> {
                    user.setAge(newUser.getAge())
                                .setEmail(newUser.getEmail())
                                .setFirstName(newUser.getFirstName())
                                .setLastName(newUser.getLastName())
                                .setMiddleName(newUser.getMiddleName())
                                .setPassword(passwordEncoder.encode(newUser.getPassword()))
                                .setUsername(newUser.getUsername());
                    return repository.save(user);
                })
                .orElseGet(() -> repository.save(newUser.setUsername(username)));

        EntityModel<UserInfoResponse> entityModel = assembler.toModel(new UserInfoResponse(updatedUser));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{username}")
    ResponseEntity<?> deleteUser(@PathVariable String username) {
        repository.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        CartPurchaseWithBooksResponse cart = new CartPurchaseWithBooksResponse();
        EntityModel<UserInfoResponse> entityModel = assembler.toModel(new UserInfoResponse(
                repository.save(newUser.setPassword(passwordEncoder.encode(newUser.getPassword())))
        ));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/{username}")
    @ResponseBody
    public EntityModel<UserInfoResponse> one(@PathVariable String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return EntityModel.of(new UserInfoResponse(user),
                linkTo(methodOn(UserController.class).one(username)).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users")
        );
    }
}
