package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.Field.FieldRequestDTO;
import org.yassir.citronix.Dto.Field.FieldResponseDTO;
import org.yassir.citronix.Mapper.IFieldMapper;
import org.yassir.citronix.Model.Entity.Farm;
import org.yassir.citronix.Model.Entity.Field;
import org.yassir.citronix.Repository.FarmRepository;
import org.yassir.citronix.Repository.FieldRepository;
import org.yassir.citronix.Service.IFieldService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldServiceImp implements IFieldService {


    private final FieldRepository fieldRepository;
    private final IFieldMapper fieldMapper;
    private final FarmRepository farmRepository;


    @Autowired
    public FieldServiceImp(FieldRepository fieldRepository, IFieldMapper fieldMapper, FarmRepository farmRepository) {
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
        this.farmRepository = farmRepository;

    }


    @Override
    public FieldResponseDTO createField(FieldRequestDTO fieldRequestDTO) {
        Field field = fieldMapper.toEntity(fieldRequestDTO);

        Farm farm = farmRepository.findById(fieldRequestDTO.farmId())
                .orElseThrow(() -> new IllegalArgumentException("Farm not found"));

        if(farm.getFields().size()>=10){
            throw new IllegalArgumentException("A farm cannot have more than 10 fields");
        }

        if(field.getArea()> farm.getTotalArea()/2){
            throw new IllegalArgumentException("A field cannot be  more than 50 % of the farm areas");
        }


        field.setFarm(farm);
        Field savedField = fieldRepository.save(field);
        return fieldMapper.toResponseDto(savedField);
    }


    @Override
    public FieldResponseDTO getFieldById(Long fieldId) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + fieldId));
        return fieldMapper.toResponseDto(field);
    }

    @Override
    public FieldResponseDTO updateField(Long id, FieldRequestDTO fieldRequestDTO) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + id));

        fieldMapper.updateEntity(fieldRequestDTO, existingField);
        Field updatedField = fieldRepository.save(existingField);
        return fieldMapper.toResponseDto(updatedField);
    }

    @Override
    public List<FieldResponseDTO> getAllFields() {
        List<Field> fields = (List<Field>) fieldRepository.findAll();
        return fields.stream()
                .map(fieldMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteField(Long fieldId) {
        if (!fieldRepository.existsById(fieldId)) {
            throw new IllegalArgumentException("Field not found with ID: " + fieldId);
        }
        fieldRepository.deleteById(fieldId);
    }


}
