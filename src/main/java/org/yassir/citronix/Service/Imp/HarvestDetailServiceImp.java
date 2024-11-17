package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailRequestDTO;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailResponseDTO;
import org.yassir.citronix.Mapper.IHarvestDetailMapper;
import org.yassir.citronix.Model.Entity.Harvest;
import org.yassir.citronix.Model.Entity.HarvestDetail;
import org.yassir.citronix.Model.Entity.Tree;
import org.yassir.citronix.Repository.HarvestDetailRepository;
import org.yassir.citronix.Repository.HarvestRepository;
import org.yassir.citronix.Repository.TreeRepository;
import org.yassir.citronix.Service.IHarvestDetailService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestDetailServiceImp implements IHarvestDetailService {


    private final HarvestDetailRepository harvestDetailRepository;
    private final IHarvestDetailMapper harvestDetailMapper;
    private final TreeRepository treeRepository;
    private final HarvestRepository harvestRepository;


    @Autowired
    public HarvestDetailServiceImp(HarvestDetailRepository harvestDetailRepository, IHarvestDetailMapper harvestDetailMapper, TreeRepository treeRepository, HarvestRepository harvestRepository) {
        this.harvestDetailRepository = harvestDetailRepository;
        this.harvestDetailMapper = harvestDetailMapper;
        this.treeRepository = treeRepository;
        this.harvestRepository = harvestRepository;
    }


    @Override
    public HarvestDetailResponseDTO createHarvestDetail(HarvestDetailRequestDTO harvestDetailRequestDTO) {

        HarvestDetail harvestDetail = harvestDetailMapper.toEntity(harvestDetailRequestDTO);

        Tree tree = treeRepository.findById(harvestDetailRequestDTO.treeId())
                .orElseThrow(() -> new IllegalArgumentException("Tree not found"));

        Harvest harvest = harvestRepository.findById(harvestDetailRequestDTO.harvestId())
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found"));

        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);

        HarvestDetail savedHarvestDetail = harvestDetailRepository.save(harvestDetail);
        return harvestDetailMapper.toResponseDto(savedHarvestDetail);
    }


    @Override
    public HarvestDetailResponseDTO getHarvestDetailById(Long harvestDetailId) {
        HarvestDetail harvestDetail = harvestDetailRepository.findById(harvestDetailId)
                .orElseThrow(() -> new IllegalArgumentException("HarvestDetail not found with ID: " + harvestDetailId));
        return harvestDetailMapper.toResponseDto(harvestDetail);
    }

    @Override
    public HarvestDetailResponseDTO updateHarvestDetail(Long id, HarvestDetailRequestDTO harvestDetailRequestDTO) {
        HarvestDetail existingHarvestDetail = harvestDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HarvestDetail not found with ID: " + id));

        harvestDetailMapper.updateEntity(harvestDetailRequestDTO, existingHarvestDetail);
        HarvestDetail updatedHarvestDetail = harvestDetailRepository.save(existingHarvestDetail);
        return harvestDetailMapper.toResponseDto(updatedHarvestDetail);
    }

    @Override
    public List<HarvestDetailResponseDTO> getAllHarvestDetails() {
        List<HarvestDetail> harvestDetails = (List<HarvestDetail>) harvestDetailRepository.findAll();
        return harvestDetails.stream()
                .map(harvestDetailMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteHarvestDetail(Long harvestDetailId) {
        if (!harvestDetailRepository.existsById(harvestDetailId)) {
            throw new IllegalArgumentException("HarvestDetail not found with ID: " + harvestDetailId);
        }
        harvestDetailRepository.deleteById(harvestDetailId);
    }

}
