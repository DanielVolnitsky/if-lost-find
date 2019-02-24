package com.daniel.iflostfind.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "missing_item")
public class MissingItem {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;

    private LocalDate lossTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "missing_item_group")
    private MissingItemGroup group;

    @Embedded
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finder_id")
    private User finder;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLossTime() {
        return lossTime;
    }

    public void setLossTime(LocalDate lossTime) {
        this.lossTime = lossTime;
    }

    public MissingItemGroup getGroup() {
        return group;
    }

    public void setGroup(MissingItemGroup group) {
        this.group = group;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getFinder() {
        return finder;
    }

    public void setFinder(User finder) {
        this.finder = finder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissingItem that = (MissingItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

