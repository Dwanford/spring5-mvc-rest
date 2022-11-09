package com.dwanford.spring5mvcrest.controllers.v1;

import com.dwanford.spring5mvcrest.api.v1.model.VendorDTO;
import com.dwanford.spring5mvcrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.dwanford.spring5mvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest {

    private static final String NAME = "Pierce & Pierce";
    private static final Long ID = 1L;
    private static final String VENDOR_URL = "/api/v1/vendors/" + ID;

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    void getAllVendors() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        VendorDTO vendor2 = new VendorDTO();
        vendor2.setName(NAME + NAME);

        List<VendorDTO> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get("/api/v1/vendors/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getVendorById() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);
        vendor.setVendorUrl(VENDOR_URL);

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor);

        mockMvc.perform(get("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    void createNewVendor() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);
        vendor.setVendorUrl(VENDOR_URL);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorUrl(vendor.getVendorUrl());

        when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VENDOR_URL)));
    }

    @Test
    void deleteVendor() throws Exception {
        mockMvc.perform(delete("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendor(anyLong());
    }

}