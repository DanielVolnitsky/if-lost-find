package com.daniel.iflostfind.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
public class User {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    //TODO make embeddable
    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    User() {

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder name(String name) {
            User.this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public Builder email(String email) {
            User.this.email = email;
            return this;
        }

        public Builder country(String country) {
            User.this.country = country;
            return this;
        }

        public Builder city(String city) {
            User.this.city = city;
            return this;
        }

        //TODO add validation
        public User build() {
            return User.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
