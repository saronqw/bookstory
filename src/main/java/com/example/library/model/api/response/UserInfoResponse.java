package com.example.library.model.api.response;

import com.example.library.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private Long age;
    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String middleName;
    private String password;
    private List<String> roles = new ArrayList<>();
    private String username;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.middleName = user.getMiddleName();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.username = user.getUsername();
    }
}
