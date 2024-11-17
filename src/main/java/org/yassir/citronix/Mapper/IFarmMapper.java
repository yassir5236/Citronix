package org.yassir.citronix.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.yassir.citronix.Dto.Farm.FarmRequestDTO;
import org.yassir.citronix.Dto.Farm.FarmResponseDTO;
import org.yassir.citronix.Model.Entity.Farm;

@Mapper(componentModel = "spring")
public interface IFarmMapper {
    Farm toEntity (FarmRequestDTO FarmRequestDTO);
    FarmResponseDTO toResponseDto (Farm farm);
    void updateEntity(FarmRequestDTO visitRequestDTO, @MappingTarget Farm farm);
}



