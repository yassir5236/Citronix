package org.yassir.citronix.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.yassir.citronix.Dto.Harvest.HarvestRequestDTO;
import org.yassir.citronix.Dto.Harvest.HarvestResponseDTO;
import org.yassir.citronix.Dto.Harvest.HarvestTotalIncomeDTO;
import org.yassir.citronix.Dto.Sale.EmbeddedSaleDTO;
import org.yassir.citronix.Model.Entity.Harvest;
import org.yassir.citronix.Model.Entity.Sale;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IHarvestMapper {
    Harvest toEntity (HarvestRequestDTO harvestRequestDTO);
    HarvestResponseDTO toResponseDto (Harvest harvest);
    void updateEntity(HarvestRequestDTO visitRequestDTO, @MappingTarget Harvest harvest);

    @Mapping(source = "sales", target = "sales", qualifiedByName = "mapSales")
    @Mapping(source = "harvest", target = "harvest")
    @Mapping(source = "totalIncome", target = "totalIncome")
    HarvestTotalIncomeDTO toTotalIncomeDTO(Harvest harvest, List<Sale> sales, double totalIncome);

    @Named("mapSales")
    default List<EmbeddedSaleDTO> mapSales(List<Sale> sales) {
        return sales.stream().map(sale -> new EmbeddedSaleDTO(
                sale.getId(),
                sale.getSaleDate(),
                sale.getClientName(), // Assurez-vous que la relation Client -> Name existe.
                sale.getIncome(),
                sale.getUnitPrice()
        )).collect(Collectors.toList());
    }
}



