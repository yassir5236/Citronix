package org.yassir.citronix.Service;

import org.yassir.citronix.Dto.Harvest.HarvestRequestDTO;
import org.yassir.citronix.Dto.Harvest.HarvestResponseDTO;

import java.util.List;

public interface IHarvestService {
    HarvestResponseDTO createHarvest(HarvestRequestDTO harvestRequestDTO);

    HarvestResponseDTO getHarvestById(Long harvestId);

    HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO harvestRequestDTO);

    List<HarvestResponseDTO> getAllHarvests();

    void deleteHarvest(Long harvestId);
}
