package com.daniel.iflostfind.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loss")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Loss {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private LocalDate lossDate;

    @Enumerated(EnumType.STRING)
    private LossGroup lossGroup;

    @Embedded
    private Coordinate coordinate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finder_id")
    private User finder;
}

