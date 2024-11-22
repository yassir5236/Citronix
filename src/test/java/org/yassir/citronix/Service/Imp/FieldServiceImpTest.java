package org.yassir.citronix.Service.Imp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.yassir.citronix.Dto.Farm.EmbeddedFarmDTO;
import org.yassir.citronix.Dto.Field.FieldRequestDTO;
import org.yassir.citronix.Dto.Field.FieldResponseDTO;
import org.yassir.citronix.Mapper.IFarmMapper;
import org.yassir.citronix.Mapper.IFieldMapper;
import org.yassir.citronix.Model.Entity.Farm;
import org.yassir.citronix.Model.Entity.Field;
import org.yassir.citronix.Repository.FieldRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FieldServiceImpTest {

    @Mock
    private IFieldMapper fieldMapper;

    @Mock
    private FieldRepository fieldRepository;

    @InjectMocks
    private FieldServiceImp fieldServiceImp;


    public FieldServiceImpTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateField() {

        Farm farm = Farm.builder()
                .id(1L)
                .build();

        Field field = Field.builder()
                .name("yassir")
                .area(1)
                .creationDate(LocalDate.now())
                .farm(farm)
                .build();


        FieldRequestDTO requestDTO = new FieldRequestDTO(
                "yassir",
                1, LocalDate.now(),
                1L);


        FieldResponseDTO responseDTO = new FieldResponseDTO(
                1L,
                "yassir",
                1,
                LocalDate.now(),
                new EmbeddedFarmDTO(null,null,null,0,null),
                new ArrayList<>()
                );

        when(fieldMapper.toEntity(requestDTO)).thenReturn(field);
        when(fieldMapper.toEntity(requestDTO)).thenReturn(field);

    }


}