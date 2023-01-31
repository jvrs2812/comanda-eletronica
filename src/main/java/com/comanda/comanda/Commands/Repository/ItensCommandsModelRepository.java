package com.comanda.comanda.Commands.Repository;

import com.comanda.comanda.Product.Repository.ProductModelRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "itens_commands")
public class ItensCommandsModelRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModelRepository product_id;

    @NotNull
    private Float unit_value;

    @NotNull
    private Float amount;

    @NotNull
    private Float totalValue;

    @NotNull
    private String obs;
}
