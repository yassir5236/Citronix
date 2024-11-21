package org.yassir.citronix.Service.Imp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.yassir.citronix.Dto.Farm.FarmRequestDTO;
import org.yassir.citronix.Dto.Farm.FarmResponseDTO;
import org.yassir.citronix.Mapper.IFarmMapper;
import org.yassir.citronix.Model.Entity.Farm;
import org.yassir.citronix.Repository.FarmRepository;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FarmServiceImpTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private IFarmMapper farmMapper;

    @InjectMocks
    private FarmServiceImp farmService;

    public FarmServiceImpTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFarm() {
        FarmRequestDTO requestDTO = new FarmRequestDTO(
                "Test Farm",
                "Test Location",
                5.0,
                LocalDate.now()
        );

        Farm farmEntity = Farm.builder()
                .name("Test Farm")
                .location("Test Location")
                .totalArea(5.0)
                .created(LocalDate.now())
                .build();

        Farm savedFarm = Farm.builder()
                .id(1L)
                .name("Test Farm")
                .location("Test Location")
                .totalArea(5.0)
                .created(LocalDate.now())
                .build();

        FarmResponseDTO responseDTO = new FarmResponseDTO(
                1L,
                "Test Farm",
                "Test Location",
                5.0,
                LocalDate.now(),
                Collections.emptyList()
        );

        when(farmMapper.toEntity(requestDTO)).thenReturn(farmEntity);
        when(farmRepository.save(farmEntity)).thenReturn(savedFarm);
        when(farmMapper.toResponseDto(savedFarm)).thenReturn(responseDTO);


        FarmResponseDTO result = farmService.createFarm(requestDTO);

        assertEquals(1L, result.id());
        assertEquals("Test Farm", result.name());
        assertEquals("Test Location", result.location());
        assertEquals(5.0, result.totalArea());

        verify(farmMapper, times(1)).toEntity(requestDTO);
        verify(farmRepository, times(1)).save(farmEntity);
        verify(farmMapper, times(1)).toResponseDto(savedFarm);
    }

}
