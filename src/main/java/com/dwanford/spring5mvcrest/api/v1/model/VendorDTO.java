package com.dwanford.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VendorDTO {

    @Schema(description = "The vendor's name")
    private String name;

    @Schema(description = "The vendor's URL")
    @JsonProperty("vendor_url")
    private String vendorUrl;

}
