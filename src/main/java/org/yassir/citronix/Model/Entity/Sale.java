package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
@Entity
@Table(name = "Sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "UnitPrice  required")
    private double unitPrice;

    @NotNull(message = "SaleDate  required")
    private LocalDate saleDate;
    @NotBlank(message = "clientName")
    private String clientName;

    @Transient
    private Double income;

    @NotNull(message = "wanted quantity  required")
    private Double wantedQuantity;


    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;


    public Double getIncome() {
        return wantedQuantity * unitPrice;
    }
}
