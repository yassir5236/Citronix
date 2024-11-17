package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.Harvest.HarvestRequestDTO;
import org.yassir.citronix.Dto.Harvest.HarvestResponseDTO;
import org.yassir.citronix.Mapper.IHarvestMapper;
import org.yassir.citronix.Model.Entity.Harvest;
import org.yassir.citronix.Repository.HarvestRepository;
import org.yassir.citronix.Service.IHarvestService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestServiceImp implements IHarvestService {



    private final HarvestRepository harvestRepository;
    private final IHarvestMapper harvestMapper;


    @Autowired
    public HarvestServiceImp(HarvestRepository harvestRepository, IHarvestMapper harvestMapper) {
        this.harvestRepository = harvestRepository;
        this.harvestMapper = harvestMapper;

    }



    @Override
    public HarvestResponseDTO createHarvest(HarvestRequestDTO harvestRequestDTO) {
        Harvest harvest = harvestMapper.toEntity(harvestRequestDTO);
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toResponseDto(savedHarvest);
    }


    @Override
    public HarvestResponseDTO getHarvestById(Long harvestId) {
        Harvest harvest = harvestRepository.findById(harvestId)
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found with ID: " + harvestId));
        return harvestMapper.toResponseDto(harvest);
    }

    @Override
    public HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO harvestRequestDTO) {
        Harvest existingHarvest = harvestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found with ID: " + id));

        harvestMapper.updateEntity(harvestRequestDTO, existingHarvest);
        Harvest updatedHarvest = harvestRepository.save(existingHarvest);
        return harvestMapper.toResponseDto(updatedHarvest);
    }

    @Override
    public List<HarvestResponseDTO> getAllHarvests() {
        List<Harvest> harvests = (List<Harvest>) harvestRepository.findAll();
        return harvests.stream()
                .map(harvestMapper::toResponseDto)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteHarvest(Long harvestId) {
        if (!harvestRepository.existsById(harvestId)) {
            throw new IllegalArgumentException("Harvest not found with ID: " + harvestId);
        }
        harvestRepository.deleteById(harvestId);
    }

}
