package com.dwanford.spring5mvcrest.api.v1.mapper;

import com.dwanford.spring5mvcrest.api.v1.model.VendorDTO;
import com.dwanford.spring5mvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendorUrl", expression = "java(\"/api/v1/vendors/\" + vendor.getId())")
    VendorDTO toDTO(Vendor vendor);
    Vendor toEntity(VendorDTO vendorDTO);

}
