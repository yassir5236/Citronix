package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.Farm.FarmRequestDTO;
import org.yassir.citronix.Dto.Farm.FarmResponseDTO;
import org.yassir.citronix.Mapper.IFarmMapper;
import org.yassir.citronix.Model.Entity.Farm;
import org.yassir.citronix.Repository.FarmRepository;
import org.yassir.citronix.Service.IFarmService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmServiceImp implements IFarmService {


    private final FarmRepository farmRepository;
    private final IFarmMapper farmMapper;


    @Autowired
    public FarmServiceImp(FarmRepository farmRepository, IFarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;

    }



    @Override
    public FarmResponseDTO createFarm(FarmRequestDTO farmRequestDTO) {
        Farm farm = farmMapper.toEntity(farmRequestDTO);
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.toResponseDto(savedFarm);
    }


    @Override
    public FarmResponseDTO getFarmById(Long farmId) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new IllegalArgumentException("Farm not found with ID: " + farmId));
        return farmMapper.toResponseDto(farm);
    }

    @Override
    public FarmResponseDTO updateFarm(Long id, FarmRequestDTO farmRequestDTO) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Farm not found with ID: " + id));

        farmMapper.updateEntity(farmRequestDTO, existingFarm);
        Farm updatedFarm = farmRepository.save(existingFarm);
        return farmMapper.toResponseDto(updatedFarm);
    }

    @Override
    public List<FarmResponseDTO> getAllFarms() {
        List<Farm> farms = (List<Farm>) farmRepository.findAll();
        return farms.stream()
                .map(farmMapper::toResponseDto)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteFarm(Long farmId) {
        if (!farmRepository.existsById(farmId)) {
            throw new IllegalArgumentException("Farm not found with ID: " + farmId);
        }
        farmRepository.deleteById(farmId);
    }

}
