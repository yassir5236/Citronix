package org.yassir.citronix.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.yassir.citronix.Dto.Harvest.HarvestRequestDTO;
import org.yassir.citronix.Dto.Harvest.HarvestResponseDTO;
import org.yassir.citronix.Model.Entity.Harvest;

@Mapper(componentModel = "spring")
public interface IHarvestMapper {
    Harvest toEntity (HarvestRequestDTO harvestRequestDTO);
    HarvestResponseDTO toResponseDto (Harvest harvest);
    void updateEntity(HarvestRequestDTO visitRequestDTO, @MappingTarget Harvest harvest);
}



