package com.daniel.iflostfind.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person")
public class User {

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "reporter", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Loss> losses = new ArrayList<>();

    @OneToMany(mappedBy = "finder", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Loss> finds = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Loss> getLosses() {
        return new ArrayList<>(losses);
    }

    public void addLoss(Loss l) {
        if (Objects.isNull(l)) {
            throw new NullPointerException("Attempt to add nullable loss");
        }
        if (Objects.nonNull(l.getReporter())) {
            throw new IllegalStateException("Loss is already owned by another person");
        }

        losses.add(l);
        l.setReporter(this);
    }

    public List<Loss> getFinds() {
        return new ArrayList<>(finds);
    }

    public void addFind(Loss f) {
        if (Objects.isNull(f)) {
            throw new NullPointerException("Attempt to add nullable find");
        }
        if (Objects.nonNull(f.getReporter())) {
            throw new IllegalStateException("Find is already owned by another person");
        }

        finds.add(f);
        f.setFinder(this);
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
