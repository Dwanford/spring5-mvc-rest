package com.dwanford.spring5mvcrest.api.v1.mapper;

import com.dwanford.spring5mvcrest.api.v1.model.VendorDTO;
import com.dwanford.spring5mvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendorMapperTest {

    private static final VendorMapper vendorMapper = VendorMapper.INSTANCE;
    private static final String NAME = "Pierce & Pierce";
    private static final Long ID = 1L;
    private static final String VENDOR_URL = "/api/v1/vendors/1";

    @Test
    void toDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO dto = vendorMapper.toDTO(vendor);

        assertEquals(vendor.getName(), dto.getName());
        assertEquals(VENDOR_URL, dto.getVendorUrl());
    }

    @Test
    void toEntity() {
        VendorDTO dto = new VendorDTO();
        dto.setName(NAME);
        dto.setVendorUrl(VENDOR_URL);

        Vendor vendor = vendorMapper.toEntity(dto);

        assertEquals(NAME, vendor.getName());
    }
}