package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailRequestDTO;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailResponseDTO;
import org.yassir.citronix.Embeddable.CompositeKey2;
import org.yassir.citronix.Mapper.IHarvestDetailMapper;
import org.yassir.citronix.Model.Entity.Field;
import org.yassir.citronix.Model.Entity.Harvest;
import org.yassir.citronix.Model.Entity.HarvestDetail;
import org.yassir.citronix.Model.Entity.Tree;
import org.yassir.citronix.Repository.HarvestDetailRepository;
import org.yassir.citronix.Repository.HarvestRepository;
import org.yassir.citronix.Repository.TreeRepository;
import org.yassir.citronix.Service.IHarvestDetailService;

import java.time.LocalDate;
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


//    @Override
//    public HarvestDetailResponseDTO createHarvestDetail(HarvestDetailRequestDTO harvestDetailRequestDTO) {
//
//        HarvestDetail harvestDetail = harvestDetailMapper.toEntity(harvestDetailRequestDTO);
//
//        Tree tree = treeRepository.findById(harvestDetailRequestDTO.treeId())
//                .orElseThrow(() -> new IllegalArgumentException("Tree not found"));
//
//        Harvest harvest = harvestRepository.findById(harvestDetailRequestDTO.harvestId())
//                .orElseThrow(() -> new IllegalArgumentException("Harvest not found"));
//
//        if(harvest.getHarvestDate().isBefore(tree.getPlantingDate())){
//            throw new IllegalArgumentException("Harvest date  cannot before the tree's plantation date");
//        }
//
//        harvestDetail.setHarvest(harvest);
//        harvestDetail.setTree(tree);
//
//        HarvestDetail savedHarvestDetail = harvestDetailRepository.save(harvestDetail);
//        return harvestDetailMapper.toResponseDto(savedHarvestDetail);
//    }



    @Override
    public HarvestDetailResponseDTO createHarvestDetail(HarvestDetailRequestDTO harvestDetailRequestDTO) {
        HarvestDetail harvestDetail = harvestDetailMapper.toEntity(harvestDetailRequestDTO);

        Tree tree = treeRepository.findById(harvestDetailRequestDTO.treeId())
                .orElseThrow(() -> new IllegalArgumentException("Tree not found"));

        Harvest harvest = harvestRepository.findById(harvestDetailRequestDTO.harvestId())
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found"));

        if(harvest.getHarvestDate().isBefore(tree.getPlantingDate())) {
            throw new IllegalArgumentException("Harvest date cannot be before the tree's plantation date");
        }

        if (isHarvestInSameSeasonForField(tree.getField(), harvest)) {
            throw new IllegalArgumentException("A harvest already exists for this field in the same season.");
        }

        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);

        HarvestDetail savedHarvestDetail = harvestDetailRepository.save(harvestDetail);

        return harvestDetailMapper.toResponseDto(savedHarvestDetail);
    }

    private boolean isHarvestInSameSeasonForField(Field field, Harvest harvest) {
        List<Tree> trees = treeRepository.findByField(field);

        List<HarvestDetail> harvestDetails = harvestDetailRepository.findByTreeIn(trees);

        for (HarvestDetail detail : harvestDetails) {
            Harvest existingHarvest = detail.getHarvest();

            if (isSameSeason(existingHarvest, harvest)) {
                return true;
            }
        }

        return false;
    }

    private boolean isSameSeason(Harvest harvest1, Harvest harvest2) {
        return harvest1.getSeason() == harvest2.getSeason();
    }






    @Override
    public HarvestDetailResponseDTO getHarvestDetailById(CompositeKey2 compositeKey2) {
        HarvestDetail harvestDetail = harvestDetailRepository.findById(compositeKey2)
                .orElseThrow(() -> new IllegalArgumentException("HarvestDetail not found with ID: " + compositeKey2));
        return harvestDetailMapper.toResponseDto(harvestDetail);
    }

    @Override
    public HarvestDetailResponseDTO updateHarvestDetail(CompositeKey2 compositeKey2, HarvestDetailRequestDTO harvestDetailRequestDTO) {
        HarvestDetail existingHarvestDetail = harvestDetailRepository.findById(compositeKey2)
                .orElseThrow(() -> new IllegalArgumentException("HarvestDetail not found with ID: " + compositeKey2));

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
    public void deleteHarvestDetail(CompositeKey2 compositeKey2 ) {
        if (!harvestDetailRepository.existsById(compositeKey2)) {
            throw new IllegalArgumentException("HarvestDetail not found with ID: " + compositeKey2);
        }
        harvestDetailRepository.deleteById(compositeKey2);
    }

}
