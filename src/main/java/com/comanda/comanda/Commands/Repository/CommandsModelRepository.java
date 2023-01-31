package com.comanda.comanda.Commands.Repository;

import com.comanda.comanda.Commands.Domain.CommandsBaseDto;
import com.comanda.comanda.Commands.Domain.CommandsGetDto;
import com.comanda.comanda.Commands.Enum.CommandsStatusEnum;
import com.comanda.comanda.Commands.Enum.CommandsTypeEnum;
import com.comanda.comanda.Table.Repository.TableModelRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "commands")
public class CommandsModelRepository {
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
    private CommandsStatusEnum status;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private CommandsTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableModelRepository table_id;

    @NotNull
    private Float discount;

    @NotNull
    private Float totalPayaple;

    public static CommandsModelRepository fromDto(CommandsBaseDto dto){
        CommandsModelRepository model = new CommandsModelRepository();

        model.id = UUID.randomUUID();
        model.createdAt = new Date();

        if(CommandsTypeEnum.values()[dto.getType()] == CommandsTypeEnum.TABLE){
            TableModelRepository table = new TableModelRepository();
            table.setId(UUID.fromString(dto.getTable_id()));
            model.table_id = table;
        }

        model.discount = 0.0F;
        model.paidAt = null;
        model.type = CommandsTypeEnum.values()[dto.getType()];
        model.status = CommandsStatusEnum.OPENED;
        model.totalPayaple = 0.0F;

        return model;
    }
    public CommandsGetDto fromModel(){
        String tableid;

        if(this.table_id == null){
            tableid = null;
        }else{
            tableid = this.table_id.getId().toString();
        }

        return new CommandsGetDto(this.id, this.createdAt, tableid, this.type.ordinal(), this.paidAt, this.status.ordinal(), this.discount, this.totalPayaple);
    }
}
