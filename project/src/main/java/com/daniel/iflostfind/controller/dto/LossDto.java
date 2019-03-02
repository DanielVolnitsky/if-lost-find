package com.daniel.iflostfind.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotBlank
    @Getter
    @Setter
    private String lossDate;

    @NotBlank
    @Getter
    @Setter
    private String type;

    @NotBlank
    @Getter
    @Setter
    private String latitude;

    @NotBlank
    @Getter
    @Setter
    private String longitude;
}
