package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  name;


    private double area ;
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "Farm_id")
    private Farm farm;

    @OneToMany(mappedBy = "field")
    private List<Tree> trees;

}
