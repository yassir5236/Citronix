package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.Harvest.HarvestRequestDTO;
import org.yassir.citronix.Dto.Harvest.HarvestResponseDTO;
import org.yassir.citronix.Dto.Harvest.HarvestTotalIncomeDTO;
import org.yassir.citronix.Mapper.IHarvestMapper;
import org.yassir.citronix.Model.Entity.Harvest;
import org.yassir.citronix.Model.Entity.Sale;
import org.yassir.citronix.Model.Entity.Tree;
import org.yassir.citronix.Model.Enum.Season;
import org.yassir.citronix.Repository.HarvestRepository;
import org.yassir.citronix.Repository.SaleRepository;
import org.yassir.citronix.Service.IHarvestService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestServiceImp implements IHarvestService {


    private final HarvestRepository harvestRepository;
    private final IHarvestMapper harvestMapper;
    private final SaleRepository saleRepository;


    @Autowired
    public HarvestServiceImp(HarvestRepository harvestRepository, IHarvestMapper harvestMapper, SaleRepository saleRepository) {
        this.harvestRepository = harvestRepository;
        this.harvestMapper = harvestMapper;
        this.saleRepository = saleRepository;
    }


    @Override
    public HarvestResponseDTO createHarvest(HarvestRequestDTO harvestRequestDTO) {
        Harvest harvest = harvestMapper.toEntity(harvestRequestDTO);

        if (!isSameSeason(harvestRequestDTO.harvestDate(), harvestRequestDTO.season())){
            throw new IllegalArgumentException("The creation date is not in the same season.");
        }


        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toResponseDto(savedHarvest);
    }


    public boolean isSameSeason(LocalDate creationDate, Season season) {
        int month = creationDate.getMonthValue();

        switch (season) {
            case SPRING:
                return month >= 3 && month <= 5;
            case SUMMER:
                return month >= 6 && month <= 8;
            case AUTUMN:
                return month >= 9 && month <= 11;
            case WINTER:
                return month == 12 || month == 1 || month == 2;
            default:
                throw new IllegalArgumentException("Unknown season: " + season);
        }
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

        if (!isSameSeason(harvestRequestDTO.harvestDate(), harvestRequestDTO.season())){
            throw new IllegalArgumentException("The creation date is not in the same season.");
        }

        existingHarvest.setSeason(harvestRequestDTO.season());
        existingHarvest.setHarvestDate(harvestRequestDTO.harvestDate());

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



    @Override
    public HarvestTotalIncomeDTO getHarvestTotalIncome(Long harvestId) {
        Harvest harvest = harvestRepository.findById(harvestId)
                .orElseThrow(() -> new RuntimeException("Harvest not found with ID: " + harvestId));

        List<Sale> sales = saleRepository.findSalesByHarvest(harvest);
        if (sales.isEmpty()) {
            throw new RuntimeException("No sales associated with this harvest.");
        }

        double totalIncome = sales.stream()
                .mapToDouble(Sale::getIncome)
                .sum();

        return harvestMapper.toTotalIncomeDTO(harvest, sales, totalIncome);
    }


}
