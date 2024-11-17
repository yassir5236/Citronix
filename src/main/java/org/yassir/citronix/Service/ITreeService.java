package org.yassir.citronix.Service;

import org.yassir.citronix.Dto.Tree.TreeRequestDTO;
import org.yassir.citronix.Dto.Tree.TreeResponseDTO;

import java.util.List;

public interface ITreeService {
    TreeResponseDTO createTree(TreeRequestDTO treeRequestDTO);

    TreeResponseDTO getTreeById(Long treeId);

    TreeResponseDTO updateTree(Long id, TreeRequestDTO treeRequestDTO);

    List<TreeResponseDTO> getAllTrees();

    void deleteTree(Long treeId);
}
