package com.comanda.comanda.Order.Repository;

import com.comanda.comanda.Order.Enum.OrderStatusEnum;
import com.comanda.comanda.Order.Enum.OrderTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "order")
public class OrderModelRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paidAt;


    @NotNull
    private OrderStatusEnum status;

    @NotNull
    private OrderTypeEnum type;
}
