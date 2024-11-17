package org.yassir.citronix.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.yassir.citronix.Dto.Sale.SaleRequestDTO;
import org.yassir.citronix.Dto.Sale.SaleResponseDTO;
import org.yassir.citronix.Model.Entity.Sale;

@Mapper(componentModel = "spring")
public interface ISaleMapper {
    Sale toEntity (SaleRequestDTO SaleRequestDTO);
    SaleResponseDTO toResponseDto (Sale sale);
    void updateEntity(SaleRequestDTO visitRequestDTO, @MappingTarget Sale sale);
}



