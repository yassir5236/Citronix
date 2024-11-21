package org.yassir.citronix.Service;

import org.yassir.citronix.Dto.Farm.FarmRequestDTO;
import org.yassir.citronix.Dto.Farm.FarmResponseDTO;

import java.util.List;

public interface IFarmService {
    FarmResponseDTO createFarm(FarmRequestDTO farmRequestDTO);

    FarmResponseDTO getFarmById(Long farmId);

    FarmResponseDTO updateFarm(Long id, FarmRequestDTO farmRequestDTO);

    List<FarmResponseDTO> getAllFarms();

    void deleteFarm(Long farmId);

     List<FarmResponseDTO> searchFarms(String name, String location, Double minArea);

    }
