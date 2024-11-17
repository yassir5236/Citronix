package org.yassir.citronix.Service;

import org.yassir.citronix.Dto.Field.FieldRequestDTO;
import org.yassir.citronix.Dto.Field.FieldResponseDTO;

import java.util.List;

public interface IFieldService {
    FieldResponseDTO createField(FieldRequestDTO fieldRequestDTO);

    FieldResponseDTO getFieldById(Long fieldId);

    FieldResponseDTO updateField(Long id, FieldRequestDTO fieldRequestDTO);

    List<FieldResponseDTO> getAllFields();

    void deleteField(Long fieldId);
}
