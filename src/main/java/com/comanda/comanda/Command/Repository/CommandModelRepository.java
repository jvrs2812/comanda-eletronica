package com.comanda.comanda.Command.Repository;

import com.comanda.comanda.Command.Domain.CommandBaseDto;
import com.comanda.comanda.Command.Domain.CommandGetDto;
import com.comanda.comanda.Command.Enum.CommandStatusEnum;
import com.comanda.comanda.Command.Enum.CommandTypeEnum;
import com.comanda.comanda.Table.Repository.TableModelRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "commands")
public class CommandModelRepository {
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
    private CommandStatusEnum status;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private CommandTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableModelRepository table_id;

    @NotNull
    private Float discount;

    @NotNull
    private Float totalPayaple;

    public static CommandModelRepository fromDto(CommandBaseDto dto){
        CommandModelRepository model = new CommandModelRepository();

        model.id = UUID.randomUUID();
        model.createdAt = new Date();

        if(CommandTypeEnum.values()[dto.getType()] == CommandTypeEnum.TABLE){
            TableModelRepository table = new TableModelRepository();
            table.setId(UUID.fromString(dto.getTable_id()));
            model.table_id = table;
        }

        model.discount = 0.0F;
        model.paidAt = null;
        model.type = CommandTypeEnum.values()[dto.getType()];
        model.status = CommandStatusEnum.OPENED;
        model.totalPayaple = 0.0F;

        return model;
    }
    public CommandGetDto fromModel(){
        String tableid;

        if(this.table_id == null){
            tableid = null;
        }else{
            tableid = this.table_id.getId().toString();
        }

        return new CommandGetDto(this.id, this.createdAt, tableid, this.type.ordinal(), this.paidAt, this.status.ordinal(), this.discount, this.totalPayaple);
    }
}
