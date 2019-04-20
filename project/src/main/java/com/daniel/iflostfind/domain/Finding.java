package com.daniel.iflostfind.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    private LocalDate dateFound;

    @Enumerated(EnumType.STRING)
    private FindingGroup findingGroup;

    @Embedded
    private DiscoveryPlace discoveryPlace;

    @OneToOne
    private User reporter;
}

