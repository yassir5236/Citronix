package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.Tree.TreeRequestDTO;
import org.yassir.citronix.Dto.Tree.TreeResponseDTO;
import org.yassir.citronix.Mapper.ITreeMapper;
import org.yassir.citronix.Model.Entity.Field;
import org.yassir.citronix.Model.Entity.Tree;
import org.yassir.citronix.Model.Enum.TreeMaturity;
import org.yassir.citronix.Repository.FieldRepository;
import org.yassir.citronix.Repository.TreeRepository;
import org.yassir.citronix.Service.ITreeService;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;
import java.time.*;

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

        if(tree.getPlantingDate().isBefore(field.getCreationDate())){
            throw new IllegalArgumentException("Tree planting date cannot before the field's creation date");
        }

        Month plantingMonth = tree.getPlantingDate().getMonth();
        if (plantingMonth.compareTo(Month.MARCH) < 0 || plantingMonth.compareTo(Month.MAY) > 0) {
            throw new IllegalArgumentException("Planting is only allowed between March and May.");
        }

        int currentTreeCount = field.getTrees().size();
        double maxTreesAllowed = field.getArea() * 100;

        if (currentTreeCount >= maxTreesAllowed) {
            throw new IllegalArgumentException("Field cannot contain more than 100 trees per hectare");
        }

        int treeAge = Period.between(tree.getPlantingDate(), LocalDate.now()).getYears();

        System.out.println(treeAge);
        if (treeAge < 3) {
            tree.setTreeMaturity(TreeMaturity.YOUNG);
            tree.setProductive(true);
        } else if (treeAge <= 10) {
            tree.setTreeMaturity(TreeMaturity.MATURE);
            tree.setProductive(true);
        } else if (treeAge <= 20) {
            tree.setTreeMaturity(TreeMaturity.OLD);
            tree.setProductive(true);
        }else {
            tree.setTreeMaturity(TreeMaturity.OLD);
            tree.setProductive(false);
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

        Field field = fieldRepository.findById(treeRequestDTO.fieldId())
                .orElseThrow(()->new IllegalArgumentException("Field not found"));



        if(treeRequestDTO.plantingDate().isBefore(field.getCreationDate())){
            throw new IllegalArgumentException("Tree planting date cannot before the field's creation date");
        }

        Month plantingMonth = treeRequestDTO.plantingDate().getMonth();
        if (plantingMonth.compareTo(Month.MARCH) < 0 || plantingMonth.compareTo(Month.MAY) > 0) {
            throw new IllegalArgumentException("Planting is only allowed between March and May.");
        }

        int currentTreeCount = field.getTrees().size();
        double maxTreesAllowed = field.getArea() * 100;

        if (currentTreeCount >= maxTreesAllowed) {
            throw new IllegalArgumentException("Field cannot contain more than 100 trees per hectare");
        }

        int treeAge = Period.between(treeRequestDTO.plantingDate(), LocalDate.now()).getYears();

        System.out.println(treeAge);
        if (treeAge < 3) {
            existingTree.setTreeMaturity(TreeMaturity.YOUNG);
            existingTree.setProductive(true);
        } else if (treeAge <= 10) {
            existingTree.setTreeMaturity(TreeMaturity.MATURE);
            existingTree.setProductive(true);
        } else if (treeAge <= 20) {
            existingTree.setTreeMaturity(TreeMaturity.OLD);
            existingTree.setProductive(true);
        }else {
            existingTree.setTreeMaturity(TreeMaturity.OLD);
            existingTree.setProductive(false);
        }


        existingTree.setField(field);
        existingTree.setPlantingDate(treeRequestDTO.plantingDate());

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
