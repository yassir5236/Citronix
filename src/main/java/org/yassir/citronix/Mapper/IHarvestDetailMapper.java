package org.yassir.citronix.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.yassir.citronix.Dto.Field.FieldRequestDTO;
import org.yassir.citronix.Dto.Field.FieldResponseDTO;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailRequestDTO;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailResponseDTO;
import org.yassir.citronix.Model.Entity.Field;
import org.yassir.citronix.Model.Entity.HarvestDetail;

@Mapper(componentModel = "spring")
public interface IHarvestDetailMapper {
    HarvestDetail toEntity (HarvestDetailRequestDTO harvestDetailRequestDTO);
    HarvestDetailResponseDTO toResponseDto (HarvestDetail harvestDetail);
    void updateEntity(HarvestDetailRequestDTO visitRequestDTO, @MappingTarget HarvestDetail harvestDetail);
}



