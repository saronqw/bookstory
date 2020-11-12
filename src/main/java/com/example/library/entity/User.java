package com.example.library.entity;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Min(value = 14, message = "The value must be >= 14")
    @NotNull(message = "The value must be not null")
    @Column()
    private Long age;

    @OneToMany(
            mappedBy = "id.user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<CartEntity> carts = new ArrayList<>();

    @Column()
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderEntity> orders = new ArrayList<>();

    @NotEmpty
    @NotNull(message = "The value must be not null")
    @Column()
    private String password;

    @OneToMany(
            mappedBy = "id.user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RatingEntity> ratings = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @NotEmpty
    @NotNull(message = "The value must be not null")
    @Size(min = 6, max = 30, message = "The value must be in the range of 6 to 30")
    @Column()
    private String username;

    public User(Long id, @NotEmpty @NotNull(message = "The value must be not null") String username, @NotEmpty @NotNull(message = "The value must be not null") @Size(min = 6, max = 30, message = "The value must be in the range of 6 to 30") String password, String firstName, String lastName, String middleName, @Min(value = 14, message = "The value must be >= 14") @NotNull(message = "The value must be not null")Long age, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
        this.email = email;
        this.roles = roles;
    }

    public User() {
    }

    private static List<String> $default$roles() {
        return new ArrayList<>();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public User setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }

    public List<RatingEntity> getRatings() {
        return ratings;
    }

    public User setRatings(List<RatingEntity> ratings) {
        this.ratings = ratings;
        return this;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $middleName = this.getMiddleName();
        result = result * PRIME + ($middleName == null ? 43 : $middleName.hashCode());
        final Object $age = this.getAge();
        result = result * PRIME + ($age == null ? 43 : $age.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $roles = this.getRoles();
        result = result * PRIME + ($roles == null ? 43 : $roles.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(orders, user.orders) &&
                Objects.equals(ratings, user.ratings) &&
                Objects.equals(age, user.age) &&
                Objects.equals(email, user.email) &&
                Objects.equals(roles, user.roles);
    }

    public String toString() {
        return "User(id=" + this.getId() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", middleName=" + this.getMiddleName() + ", age=" + this.getAge() + ", email=" + this.getEmail() + ", roles=" + this.getRoles() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public @Min(value = 14, message = "The value must be >= 14") @NotNull(message = "The value must be not null") Long getAge() {
        return this.age;
    }

    public String getEmail() {
        return this.email;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public User setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setAge(Long age) {
        this.age = age;
        return this;
    }

    public User setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public User setCarts(List<CartEntity> carts) {
        this.carts = carts;
        return this;
    }

    public static class UserBuilder {
        private @Min(value = 14, message = "The value must be >= 14") @NotNull(message = "The value must be not null") Long age;
        private String email;
        private String firstName;
        private Long id;
        private String lastName;
        private String middleName;
        private @NotEmpty @NotNull(message = "The value must be not null") @Size(min = 6, max = 30, message = "The value must be in the range of 6 to 30") String password;
        private boolean roles$set;
        private List<String> roles$value;
        private @NotEmpty @NotNull(message = "The value must be not null") String username;

        UserBuilder() {
        }

        public User.UserBuilder age(@Min(value = 14, message = "The value must be >= 14") @NotNull(message = "The value must be not null") Long age) {
            this.age = age;
            return this;
        }

        public User build() {
            List<String> roles$value = this.roles$value;
            if (!this.roles$set) {
                roles$value = User.$default$roles();
            }
            return new User(id, username, password, firstName, lastName, middleName, age, email, roles$value);
        }

        public User.UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User.UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public User.UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public User.UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public User.UserBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public User.UserBuilder password(@NotEmpty @NotNull(message = "The value must be not null") @Size(min = 6, max = 30, message = "The value must be in the range of 6 to 30") String password) {
            this.password = password;
            return this;
        }

        public User.UserBuilder roles(List<String> roles) {
            this.roles$value = roles;
            this.roles$set = true;
            return this;
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", middleName=" + this.middleName + ", age=" + this.age + ", email=" + this.email + ", roles$value=" + this.roles$value + ")";
        }

        public User.UserBuilder username(@NotEmpty @NotNull(message = "The value must be not null") String username) {
            this.username = username;
            return this;
        }
    }
}
