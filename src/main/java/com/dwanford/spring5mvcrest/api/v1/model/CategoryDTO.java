package com.dwanford.spring5mvcrest.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryDTO {
    @Schema(description = "The category's id", required = true)
    private Long id;

    @Schema(description = "The category's name", required = true)
    private String name;
}
