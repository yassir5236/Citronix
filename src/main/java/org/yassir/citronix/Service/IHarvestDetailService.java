package org.yassir.citronix.Service;

import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailRequestDTO;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailResponseDTO;

import java.util.List;

public interface IHarvestDetailService {
    HarvestDetailResponseDTO createHarvestDetail(HarvestDetailRequestDTO HarvestDetailRequestDTO);

    HarvestDetailResponseDTO getHarvestDetailById(Long harvestDetailId);

    HarvestDetailResponseDTO updateHarvestDetail(Long id, HarvestDetailRequestDTO harvestDetailRequestDTO);

    List<HarvestDetailResponseDTO> getAllHarvestDetails();

    void deleteHarvestDetail(Long harvestDetailId);
}
