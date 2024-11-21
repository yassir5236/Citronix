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
import java.util.List;
import java.util.Optional;

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

    @Test
    void testGetFarmById() {
        Long farmId = 1L;
        Farm farm = Farm.builder()
                .id(farmId)
                .name("Test Farm")
                .location("Test Location")
                .totalArea(5.0)
                .created(LocalDate.now())
                .build();

        FarmResponseDTO responseDTO = new FarmResponseDTO(
                farmId,
                "Test Farm",
                "Test Location",
                5.0,
                LocalDate.now(),
                Collections.emptyList()
        );

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));
        when(farmMapper.toResponseDto(farm)).thenReturn(responseDTO);

        FarmResponseDTO result = farmService.getFarmById(farmId);


        assertEquals(farmId, result.id());
        assertEquals("Test Farm", result.name());
        verify(farmRepository, times(1)).findById(farmId);
        verify(farmMapper, times(1)).toResponseDto(farm);
    }


    @Test
    void testUpdateFarm() {
        Long farmId = 1L;
        FarmRequestDTO requestDTO = new FarmRequestDTO("Updated Farm", "Updated Location", 10.0, LocalDate.now());

        Farm existingFarm = Farm.builder()
                .id(farmId)
                .name("Test Farm")
                .location("Test Location")
                .totalArea(5.0)
                .created(LocalDate.now())
                .build();

        Farm updatedFarm = Farm.builder()
                .id(farmId)
                .name("Updated Farm")
                .location("Updated Location")
                .totalArea(10.0)
                .created(LocalDate.now())
                .build();

        FarmResponseDTO responseDTO = new FarmResponseDTO(
                farmId,
                "Updated Farm",
                "Updated Location",
                10.0,
                LocalDate.now(),
                Collections.emptyList()
        );

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(existingFarm));
        doNothing().when(farmMapper).updateEntity(requestDTO, existingFarm);
        when(farmRepository.save(existingFarm)).thenReturn(updatedFarm);
        when(farmMapper.toResponseDto(updatedFarm)).thenReturn(responseDTO);

        FarmResponseDTO result = farmService.updateFarm(farmId, requestDTO);

        assertEquals(farmId, result.id());
        assertEquals("Updated Farm", result.name());
        assertEquals("Updated Location", result.location());
        assertEquals(10.0, result.totalArea());

        verify(farmRepository, times(1)).findById(farmId);
        verify(farmMapper, times(1)).updateEntity(requestDTO, existingFarm);
        verify(farmRepository, times(1)).save(existingFarm);
        verify(farmMapper, times(1)).toResponseDto(updatedFarm);
    }



    @Test
    void testGetAllFarms() {
        Farm farm1 = Farm.builder()
                .id(1L)
                .name("Farm 1")
                .location("Location 1")
                .totalArea(5.0)
                .created(LocalDate.now())
                .build();

        Farm farm2 = Farm.builder()
                .id(2L)
                .name("Farm 2")
                .location("Location 2")
                .totalArea(10.0)
                .created(LocalDate.now())
                .build();

        List<Farm> farms = List.of(farm1, farm2);

        FarmResponseDTO responseDTO1 = new FarmResponseDTO(1L, "Farm 1", "Location 1", 5.0, LocalDate.now(), Collections.emptyList());
        FarmResponseDTO responseDTO2 = new FarmResponseDTO(2L, "Farm 2", "Location 2", 10.0, LocalDate.now(), Collections.emptyList());

        List<FarmResponseDTO> responseDTOs = List.of(responseDTO1, responseDTO2);

        when(farmRepository.findAll()).thenReturn(farms);
        when(farmMapper.toResponseDto(farm1)).thenReturn(responseDTO1);
        when(farmMapper.toResponseDto(farm2)).thenReturn(responseDTO2);

        List<FarmResponseDTO> result = farmService.getAllFarms();

        assertEquals(2, result.size());
        assertEquals("Farm 1", result.get(0).name());
        assertEquals("Farm 2", result.get(1).name());

        verify(farmRepository, times(1)).findAll();
        verify(farmMapper, times(1)).toResponseDto(farm1);
        verify(farmMapper, times(1)).toResponseDto(farm2);
    }




    @Test
    void testDeleteFarm() {
        Long farmId = 1L;

        when(farmRepository.existsById(farmId)).thenReturn(true);
        doNothing().when(farmRepository).deleteById(farmId);

        farmService.deleteFarm(farmId);

        verify(farmRepository, times(1)).existsById(farmId);
        verify(farmRepository, times(1)).deleteById(farmId);
    }



}
