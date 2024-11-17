package org.yassir.citronix.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.yassir.citronix.Dto.Farm.FarmResponseDTO;
import org.yassir.citronix.Dto.Field.FieldRequestDTO;
import org.yassir.citronix.Dto.Field.FieldResponseDTO;
import org.yassir.citronix.Model.Entity.Farm;
import org.yassir.citronix.Model.Entity.Field;

@Mapper(componentModel = "spring")
public interface IFieldMapper {
    Field toEntity (FieldRequestDTO fieldRequestDTO);
    FieldResponseDTO toResponseDto (Field field);
    void updateEntity(FieldRequestDTO visitRequestDTO, @MappingTarget Field field);
}



