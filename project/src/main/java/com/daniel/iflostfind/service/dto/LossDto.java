package com.daniel.iflostfind.service.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class LossDto {

    @Getter
    @Setter
    private Long id;

    @NotBlank
    @Getter
    @Setter
    private String name;

    @NotNull
    @Getter
    @Setter
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Getter
    @Setter
    private LocalDate lossDate;

    @NotBlank
    @Getter
    @Setter
    private String type;

    @NonNull
    @Getter
    @Setter
    private Double latitude;

    @NonNull
    @Getter
    @Setter
    private Double longitude;
}
