package com.example.library.controller.assemblers;

import com.example.library.controller.UserController;
import com.example.library.model.api.response.UserInfoResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserInfoResponse, EntityModel<UserInfoResponse>> {
    @Override
    public EntityModel<UserInfoResponse> toModel(UserInfoResponse user) {
        return EntityModel.of(user, //
                linkTo(methodOn(UserController.class).one(user.getUsername())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}
