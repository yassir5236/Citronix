package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message ="UnitPrice date required")
    private double unitPrice;

    @NotNull(message ="SaleDate date required")
    private LocalDate saleDate;
    @NotBlank(message = "clientName")
    private String clientName;



    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

}
