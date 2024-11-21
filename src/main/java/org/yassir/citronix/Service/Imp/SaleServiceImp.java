package org.yassir.citronix.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yassir.citronix.Dto.Sale.SaleRequestDTO;
import org.yassir.citronix.Dto.Sale.SaleResponseDTO;
import org.yassir.citronix.Mapper.ISaleMapper;
import org.yassir.citronix.Model.Entity.Harvest;
import org.yassir.citronix.Model.Entity.Sale;
import org.yassir.citronix.Repository.HarvestRepository;
import org.yassir.citronix.Repository.SaleRepository;
import org.yassir.citronix.Service.ISaleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImp implements ISaleService {


    private final SaleRepository saleRepository;
    private final ISaleMapper saleMapper;
    private final HarvestRepository harvestRepository;


    @Autowired
    public SaleServiceImp(SaleRepository saleRepository, ISaleMapper saleMapper, HarvestRepository harvestRepository) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.harvestRepository = harvestRepository;
    }



    @Override
    public SaleResponseDTO createSale(SaleRequestDTO saleRequestDTO) {

        Sale sale = saleMapper.toEntity(saleRequestDTO);

        Harvest harvest =harvestRepository.findById(saleRequestDTO.harvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));

        if (harvest.getHarvestDate().isBefore(sale.getSaleDate())) {
            throw new IllegalArgumentException("Sale date cannot be before the harvest's  date");
        }

        if(saleRequestDTO.wantedQuantity() > harvest.getTotalQuantity()){
            throw new IllegalArgumentException("Wanted quantity cannot be greater than harvest's total quantity");
        }

        harvest.setTotalQuantity(harvest.getTotalQuantity() - saleRequestDTO.wantedQuantity());

        System.out.println(sale.getIncome());

        sale.setHarvest(harvest);
        Sale savedSale = saleRepository.save(sale);
        return saleMapper.toResponseDto(savedSale);
    }


    @Override
    public SaleResponseDTO getSaleById(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with ID: " + saleId));
        return saleMapper.toResponseDto(sale);
    }

    @Override
    public SaleResponseDTO updateSale(Long id, SaleRequestDTO saleRequestDTO) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with ID: " + id));

        saleMapper.updateEntity(saleRequestDTO, existingSale);
        Sale updatedSale = saleRepository.save(existingSale);
        return saleMapper.toResponseDto(updatedSale);
    }

    @Override
    public List<SaleResponseDTO> getAllSales() {
        List<Sale> sales = (List<Sale>) saleRepository.findAll();
        return sales.stream()
                .map(saleMapper::toResponseDto)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteSale(Long saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new IllegalArgumentException("Sale not found with ID: " + saleId);
        }
        saleRepository.deleteById(saleId);
    }



}
