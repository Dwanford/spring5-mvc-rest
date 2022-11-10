package com.dwanford.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerDTO {

    @Schema(description = "The customer's first name", required = true)
    private String firstName;
    @Schema(description = "The customer's last name")
    private String lastName;

    @Schema(description = "The customer's URL")
    @JsonProperty("customer_url")
    private String customerUrl;
}
