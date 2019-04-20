package com.daniel.iflostfind.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindingWithReporterDto extends FindingDto {
    private UserDto reporter;
}
