package org.yassir.citronix.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.yassir.citronix.Dto.Tree.TreeRequestDTO;
import org.yassir.citronix.Dto.Tree.TreeResponseDTO;
import org.yassir.citronix.Model.Entity.Tree;

@Mapper(componentModel = "spring")
public interface ITreeMapper {
    Tree toEntity (TreeRequestDTO treeRequestDTO);
    TreeResponseDTO toResponseDto (Tree tree);
    void updateEntity(TreeRequestDTO visitRequestDTO, @MappingTarget Tree tree);
}



