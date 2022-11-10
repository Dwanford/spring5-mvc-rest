package com.dwanford.spring5mvcrest.controllers.v1;

import com.dwanford.spring5mvcrest.api.v1.model.VendorDTO;
import com.dwanford.spring5mvcrest.services.VendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Vendor", description = "This is Vendor Controller")
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;


    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Operation(summary = "This lists all vendors", description = "More detailed description goes here")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<VendorDTO> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @Operation(summary = "This returns vendor by ID value")
    @GetMapping("/{id}")
    VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @Operation(summary = "This creates new vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }

    @Operation(summary = "This updates old vendor with new customer")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @Operation(summary = "This patching vendor")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @Operation(summary = "This deletes vendor")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }
}
