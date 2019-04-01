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
public class LossDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lossDate;

    @NotBlank
    private String type;

    @NonNull
    private Double latitude;

    @NonNull
    private Double longitude;
}
