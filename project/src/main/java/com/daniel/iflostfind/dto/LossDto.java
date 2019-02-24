package com.daniel.iflostfind.dto;

import lombok.Getter;
import lombok.Setter;

public class LossDto {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String lossDate;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String latitude;

    @Getter
    @Setter
    private String longitude;
}
