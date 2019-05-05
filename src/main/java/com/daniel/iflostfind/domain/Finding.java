package com.daniel.iflostfind.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "finding")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Finding {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @Column(nullable = false)
    private LocalDate dateFound;

    @Enumerated(EnumType.STRING)
    private FindingGroup findingGroup;

    @Embedded
    private DiscoveryPlace discoveryPlace;

    @Transient
    private long daysOld;

    @OneToOne
    private User reporter;

    @PostLoad
    private void onLoad() {
        this.daysOld = ChronoUnit.DAYS.between(this.dateFound, LocalDate.now());
    }
}

