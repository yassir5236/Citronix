package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.Tree.TreeRequestDTO;
import org.yassir.citronix.Dto.Tree.TreeResponseDTO;
import org.yassir.citronix.Mapper.ITreeMapper;
import org.yassir.citronix.Model.Entity.Field;
import org.yassir.citronix.Model.Entity.Tree;
import org.yassir.citronix.Repository.FieldRepository;
import org.yassir.citronix.Repository.TreeRepository;
import org.yassir.citronix.Service.ITreeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeServiceImp implements ITreeService {

    private final TreeRepository treeRepository;
    private final ITreeMapper treeMapper;
    private final FieldRepository fieldRepository;


    @Autowired
    public TreeServiceImp(TreeRepository treeRepository, ITreeMapper treeMapper, FieldRepository fieldRepository) {
        this.treeRepository = treeRepository;
        this.treeMapper = treeMapper;
        this.fieldRepository = fieldRepository;
    }



    @Override
    public TreeResponseDTO createTree(TreeRequestDTO treeRequestDTO) {
        Tree tree = treeMapper.toEntity(treeRequestDTO);
        Field field =fieldRepository.findById(treeRequestDTO.fieldId())
                .orElseThrow(()->new IllegalArgumentException("Field not found"));

        int currentTreeCount = field.getTrees().size();
        double maxTreesAllowed = field.getArea() * 100;

        if (currentTreeCount >= maxTreesAllowed) {
            throw new IllegalArgumentException("Field cannot contain more than 100 trees per hectare");
        }

        tree.setField(field);
        Tree savedTree = treeRepository.save(tree);
        return treeMapper.toResponseDto(savedTree);
    }


    @Override
    public TreeResponseDTO getTreeById(Long treeId) {
        Tree tree = treeRepository.findById(treeId)
                .orElseThrow(() -> new IllegalArgumentException("Tree not found with ID: " + treeId));
        return treeMapper.toResponseDto(tree);
    }

    @Override
    public TreeResponseDTO updateTree(Long id, TreeRequestDTO treeRequestDTO) {
        Tree existingTree = treeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tree not found with ID: " + id));

        treeMapper.updateEntity(treeRequestDTO, existingTree);
        Tree updatedTree = treeRepository.save(existingTree);
        return treeMapper.toResponseDto(updatedTree);
    }

    @Override
    public List<TreeResponseDTO> getAllTrees() {
        List<Tree> trees = (List<Tree>) treeRepository.findAll();
        return trees.stream()
                .map(treeMapper::toResponseDto)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteTree(Long treeId) {
        if (!treeRepository.existsById(treeId)) {
            throw new IllegalArgumentException("Tree not found with ID: " + treeId);
        }
        treeRepository.deleteById(treeId);
    }


}
