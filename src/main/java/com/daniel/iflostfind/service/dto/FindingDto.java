package com.daniel.iflostfind.service.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FindingDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFound;

    @NotBlank
    private String findingGroupName;

    @NonNull
    private Double latitude;

    @NonNull
    private Double longitude;

    @NotBlank
    private String discoveryPlaceId;

    private long daysOld;
}
