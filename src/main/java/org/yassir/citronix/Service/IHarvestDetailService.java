package org.yassir.citronix.Service;

import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailRequestDTO;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailResponseDTO;
import org.yassir.citronix.Embeddable.CompositeKey2;

import java.util.List;

public interface IHarvestDetailService {
    HarvestDetailResponseDTO createHarvestDetail(HarvestDetailRequestDTO HarvestDetailRequestDTO);

    HarvestDetailResponseDTO getHarvestDetailById(CompositeKey2 compositeKey2);

     HarvestDetailResponseDTO updateHarvestDetail(CompositeKey2 compositeKey2, HarvestDetailRequestDTO harvestDetailRequestDTO);

    List<HarvestDetailResponseDTO> getAllHarvestDetails();

    void deleteHarvestDetail(CompositeKey2 compositeKey2 );
}
